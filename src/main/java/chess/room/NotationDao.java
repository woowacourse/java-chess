package chess.room;

import chess.domain.board.Square;
import chess.renderer.FileRenderer;
import chess.renderer.RankRenderer;

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

    public static void addNotation(final int roomId, final int turn, final Move move) throws SQLException {
        String query = "insert into notation values(?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        preparedStatement.setString(2, move.getSourceString());
        preparedStatement.setString(3, move.getTargetString());
        preparedStatement.setInt(4, turn);
        preparedStatement.executeUpdate();
    }

    public static Notation findByRoomId(final int roomId) throws SQLException {
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

    public static int findLastTurn(final int roomId) throws SQLException {
        int turn = 1;
        String query = "select turn from notation where room_id =?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            turn = resultSet.getInt(1);
        }
        return turn;
    }

    private static Move makeMove(final ResultSet resultSet) throws SQLException {
        return new Move(makeSquare(resultSet.getString(1))
                , makeSquare(resultSet.getString(2)));
    }

    private static Square makeSquare(final String fileAndRank) {
        return new Square(FileRenderer.renderString(String.valueOf(fileAndRank.charAt(0))),
                RankRenderer.renderString(String.valueOf(fileAndRank.charAt(1))));
    }

    public static void deleteByRoomId(final int roomId) throws SQLException {
        final String query = "delete from notation where room_id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);
        preparedStatement.executeUpdate();
    }
}
