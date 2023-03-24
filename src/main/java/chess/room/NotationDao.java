package chess.room;

import chess.domain.board.Square;
import chess.renderer.FileInputRenderer;
import chess.renderer.RankInputRenderer;

import java.sql.*;

public class NotationDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void addNotation(int roomId, int turn, Move move) throws SQLException {
        String query = "insert into notation values(?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        preparedStatement.setString(2, move.getSourceString());
        preparedStatement.setString(3, move.getTargetString());
        preparedStatement.setInt(4, turn);
        preparedStatement.executeUpdate();
    }

    public static Notation findByRoomId(int roomId) throws SQLException {
        String query = "select source,target from notation where room_id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Notation notation = new Notation();
        while (resultSet.next()) {
            notation.add(makeMove(resultSet));
        }
        return notation;
    }

    private static Move makeMove(ResultSet resultSet) throws SQLException {
        return new Move(makeSquare(resultSet.getString(1))
                , makeSquare(resultSet.getString(2)));
    }

    private static Square makeSquare(String fileAndRank) {
        return new Square(FileInputRenderer.renderString(String.valueOf(fileAndRank.charAt(0))),
                RankInputRenderer.renderString(String.valueOf(fileAndRank.charAt(1))));
    }
}
