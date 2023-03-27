package dao;

import domain.game.Game;
import domain.game.Position;
import domain.game.Side;
import domain.piece.Piece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.FileMapper;
import util.PieceMapper;
import util.PositionMapper;
import util.RankMapper;
import util.SideMapper;

public class GameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

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

    public void create(Game game) {
        Side sideOfTurn = game.getSideOfTurn();
        String query = "INSERT INTO chess_game(user_name, title, turn) VALUES (?, ?, ?)";
        ResultSet resultSet;
        String[] returnId = {"game_id"};
        int gameId = 0;
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query, returnId)) {
            preparedStatement.setString(1, game.getUserName());
            preparedStatement.setString(2, game.getTitle());
            preparedStatement.setString(3, sideOfTurn.name());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys(); // 쿼리 실행 후 생성된 키 값 반환
            if (resultSet.next()) {
                gameId = resultSet.getInt(1); // 키값 초기화
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        saveChessBoard(game, gameId);
    }

    public void saveChessBoard(Game game) {
        String query = "SELECT id FROM chess_game WHERE (user_name = ? AND title = ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, game.getUserName());
            preparedStatement.setString(2, game.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String gameId = resultSet.getString("id");
                this.saveChessBoard(game, Integer.parseInt(gameId));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveChessBoard(Game game, int gameId) {
        Map<Position, Piece> chessBoard = game.getChessBoard();
        String query;
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            String positionText = PositionMapper.convertPositionToText(positionPieceEntry.getKey());
            String pieceText = positionPieceEntry.getValue().getCategory().name();
            query = "UPDATE chess_game SET " + positionText + " = ? WHERE id = ?";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pieceText);
                preparedStatement.setString(2, String.valueOf(gameId));
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
        query = "UPDATE chess_game SET turn = ? WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, game.getSideOfTurn().name());
            preparedStatement.setString(2, String.valueOf(gameId));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int chessBoardId) {
        String query = "DELETE FROM chess_game WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(chessBoardId));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GameDto> findGamesByUserName(String userName) {
        String query = "SELECT id, user_name ,title FROM chess_game WHERE user_name = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GameDto> gameDtos = new ArrayList<>();
            while (resultSet.next()) {
                gameDtos.add(new GameDto(
                        resultSet.getString("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("title")
                ));
            }
            return gameDtos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Game findGameById(String gameId) {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Game game = null;
            if (resultSet.next()) {
                Map<Position, Piece> chessBoard = makeChessBoardByDB(resultSet);
                game = new Game(
                        chessBoard,
                        resultSet.getString("user_name"),
                        resultSet.getString("title"),
                        SideMapper.convertTextToSide(resultSet.getString("turn"))
                );
            }
            return game;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<Position, Piece> makeChessBoardByDB(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (RankMapper rankMapper : RankMapper.values()) {
            for (FileMapper fileMapper : FileMapper.values()) {
                String positionText = fileMapper.getText() + rankMapper.getText();
                chessBoard.put(
                        PositionMapper.convertTextToPosition(positionText),
                        PieceMapper.convertTextForDBToPiece(resultSet.getString(positionText)));
            }
        }
        return chessBoard;
    }
}
