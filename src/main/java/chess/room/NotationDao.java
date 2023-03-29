package chess.room;

import chess.domain.board.Square;
import chess.renderer.FileRenderer;
import chess.renderer.RankRenderer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotationDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addNotation(final Room room, final Move move) throws SQLException {
        try (final Connection connection = getConnection()) {
            final String query = "insert into notation values(?,?,?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getRoomId());
            preparedStatement.setString(2, move.getSourceString());
            preparedStatement.setString(3, move.getTargetString());
            preparedStatement.setInt(4, room.getTurn());
            preparedStatement.executeUpdate();
        }
    }

    public List<Move> findByRoomId(final int roomId) throws SQLException {
        try (final Connection connection = getConnection()) {
            final String query = "select source,target from notation where room_id = ?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return getNotation(resultSet);
        }
    }

    private List<Move> getNotation(ResultSet resultSet) throws SQLException {
        List<Move> notation = new ArrayList<>();
        while (resultSet.next()) {
            notation.add(makeMove(resultSet));
        }
        return notation;
    }

    public int findLastTurn(final int roomId) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "select turn from notation where room_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return findTurn(resultSet);
        }
    }

    private static int findTurn(ResultSet resultSet) throws SQLException {
        int turn = 1;
        while (resultSet.next()) {
            turn = resultSet.getInt(1);
        }
        return turn;
    }

    private Move makeMove(final ResultSet resultSet) throws SQLException {
        return new Move(makeSquare(resultSet.getString(1))
                , makeSquare(resultSet.getString(2)));
    }

    private Square makeSquare(final String fileAndRank) {
        return new Square(FileRenderer.renderString(String.valueOf(fileAndRank.charAt(0))),
                RankRenderer.renderString(String.valueOf(fileAndRank.charAt(1))));
    }

    public void deleteByRoomId(final int roomId) throws SQLException {
        try (Connection connection = getConnection()) {
            final String query = "delete from notation where room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        }
    }
}
