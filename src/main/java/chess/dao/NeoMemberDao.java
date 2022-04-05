package chess.dao;

import java.sql.*;

public class NeoMemberDao {

    public static final int GAME_MEMBER_COUNT = 2;
    private final ConnectionManager connectionManager;

    public NeoMemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public NeoMember save(String name, int boardId) {
        if (countByBoardId(boardId) >= GAME_MEMBER_COUNT) {
            throw new IllegalArgumentException("한 게임에는 참여자가 " + GAME_MEMBER_COUNT + "명을 초과할 수 없습니다.");
        }
        ConnectionFunction<Connection, NeoMember> runnable = connection -> {
            String sql = "insert into neo_member(name, board_id) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, boardId);
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("did not save");
            }
            return new NeoMember(generatedKeys.getInt(1), name, boardId);
        };
        return connectionManager.run(runnable);
    }

    private int countByBoardId(int boardId) {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            String sql = "select count(*) as countMembers from neo_member where board_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다~");
            }
            return resultSet.getInt("countMembers");
        };
        return connectionManager.run(runnable);
    }
}
