package chess.dao;

import java.sql.*;

public class NeoMemberDao {

    private final ConnectionManager connectionManager;

    public NeoMemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public NeoMember save(String name, int boardId) {
        final Connection connection = connectionManager.getConnection();
        if (countByBoardId(connection, boardId) >= 2) {
            throw new IllegalArgumentException();
        }
        String sql = "insert into neo_member(name, board_id) values(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, boardId);
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("did not save");
            }
            return new NeoMember(generatedKeys.getInt(1), name, boardId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.close(connection);
        }
    }

    private int countByBoardId(Connection connection, int boardId) {
        String sql = "select count(*) as countMembers from neo_member where board_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다~");
            }
            return resultSet.getInt("countMembers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
