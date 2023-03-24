package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public final class ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final Connection connection;

    public ChessGameDao() {
        this.connection = getConnection();
    }

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(ChessGame chessGame) {
        Map<Position, Piece> board = chessGame.getBoard();
        String turn = chessGame.getTurn();

        saveTurn(turn);

        Integer recentSavedId = findRecentSavedId();

        board.forEach(
                (position, piece) -> {

                    String sql = "INSERT INTO board SET " +
                            "position = ?," +
                            "piece = ?," +
                            "color = ?," +
                            "game_id = ?";
                    try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setString(1, position.toString());
                        preparedStatement.setString(2, piece.getName());
                        preparedStatement.setString(3, piece.getColor().name());
                        preparedStatement.setString(3, piece.getColor().name());
                        preparedStatement.setInt(4, recentSavedId);
                        preparedStatement.executeUpdate();
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        if (!generatedKeys.next()) {
                            return;
                        }
                        long aLong = generatedKeys.getLong(1);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    public Integer findRecentSavedId() {
        String sql = "SELECT game_id FROM game ORDER BY game_id DESC LIMIT 1";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("game_id");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    private void saveTurn(final String turn) {
        String sql = "INSERT INTO game SET turn = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public ChessGame load(int gameId) {
        String turn = findTurnById(gameId);
        Map<Position, Piece> board = new HashMap<>();

        String sql = "SELECT * FROM board WHERE game_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String piece = resultSet.getString("piece");
                String color = resultSet.getString("color");
                Kind kind = Kind.valueOf(piece);
                board.put(Position.from(position), kind.toPiece(color));
            }

            return ChessGame.load(new Board(board), Color.valueOf(turn));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String findTurnById(int gameId) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
