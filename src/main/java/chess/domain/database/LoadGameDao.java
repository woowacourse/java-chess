package chess.domain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.dto.GameDto;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class LoadGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private final String database;

    public LoadGameDao(final Database database) {
        this.database = database.getName();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + database + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<GameDto> getGamesById(String id) {
        final String query = "SELECT * FROM Game WHERE user_id = ? ORDER BY created_at DESC";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GameDto> gameDtos = new ArrayList<>();
            while (resultSet.next()) {
                String gameId = String.valueOf(resultSet.getLong("game_id"));
                String createdAt = resultSet.getTimestamp("created_at").toString();
                GameDto gameDto = new GameDto(gameId, createdAt);
                gameDtos.add(gameDto);
            }
            return gameDtos;
        } catch (SQLException e) {
            throw new IllegalArgumentException("게임을 불러오는데 실패했습니다.");
        }
    }

    public int getLastTurnById(String gameId) {
        final String query = "SELECT MAX(turn) AS result FROM Board WHERE game_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, Integer.parseInt(gameId));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("result");
        } catch (SQLException e) {
            throw new IllegalArgumentException("등록되지 않은 아이디입니다.");
        }
    }

    public ChessGame getGameById(String gameId, int turn) {
        final String query = "SELECT * FROM Board WHERE game_id = ? and turn = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            Map<Square, Piece> board = new HashMap<>();
            preparedStatement.setInt(1, Integer.parseInt(gameId));
            preparedStatement.setInt(2, turn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                char file = resultSet.getString("piece_file").charAt(0);
                char rank = resultSet.getString("piece_rank").charAt(0);
                Square square = Square.of(File.from(file), Rank.from(rank));
                String pieceType = resultSet.getString("piece_type");
                int teamValue = resultSet.getInt("piece_team");
                try {
                    Piece piece = PieceName.toPiece(pieceType, Team.from(teamValue));
                    board.put(square, piece);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException("기물을 불러오지 못했습니다.");
                }
            }
            return new ChessGame(new Board(board), new Turn(turn));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createGame(String userId) {
        final String query = "INSERT INTO Game (user_id) VALUES (?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("게임 생성에 실패했습니다.");
        }
    }

    public String getLastGameId(String userId) {
        final String query = "SELECT MAX(game_id) AS result FROM Game WHERE user_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("result");
        } catch (SQLException e) {
            throw new IllegalArgumentException("가장 최근에 생성된 게임을 불러오는데 실패했습니다.");
        }
    }
}
