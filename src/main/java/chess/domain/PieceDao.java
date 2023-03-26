package chess.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PieceDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

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

    public void saveNewGame(List<PieceEntity> pieces) {
        StringBuilder newGamePiecesSaveQuery = getBulkPiecesInsertQuery(pieces);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(newGamePiecesSaveQuery.toString())) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder getBulkPiecesInsertQuery(List<PieceEntity> pieces) {
        StringBuilder newGamePiecesSaveQuery = new StringBuilder(
                "INSERT INTO piece (position, type, camp, board_id) VALUES");
        for (PieceEntity piece : pieces) {
            newGamePiecesSaveQuery.append(" ('")
                    .append(piece.getPosition()).append("', '")
                    .append(piece.getPieceType()).append("', '")
                    .append(piece.getCamp()).append("', ")
                    .append(piece.getBoardId()).append("),");
        }

        newGamePiecesSaveQuery.deleteCharAt(newGamePiecesSaveQuery.length() - 1);
        newGamePiecesSaveQuery.append(";");
        return newGamePiecesSaveQuery;
    }
}
