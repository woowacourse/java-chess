package dao;

import domain.game.Game;
import domain.game.Position;
import domain.game.Side;
import domain.piece.Piece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import util.PositionMapper;

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
        String query = "INSERT INTO chess_game(turn) VALUES (?)";
        ResultSet resultSet;
        String[] returnId = {"game_id"};
        int gameId = 0;
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query, returnId)) {
            preparedStatement.setString(1, sideOfTurn.name());
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

    public void saveChessBoard(Game game, int gameId) {
        Map<Position, Piece> chessBoard = game.getChessBoard();
        String query;
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            String positionText = PositionMapper.convertPositionToString(positionPieceEntry.getKey());
            String pieceText = positionPieceEntry.getValue().getCategory().name();
            query = "UPDATE chess_game set " + positionText + " = ? where id = ?";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pieceText);
                preparedStatement.setString(2, String.valueOf(gameId));
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
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
}
