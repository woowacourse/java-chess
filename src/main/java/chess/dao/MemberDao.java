package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public static final int GAME_MEMBER_COUNT = 2;
    private final ConnectionManager connectionManager;

    public MemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Member save(String name, int boardId) {
        return connectionManager.executeQuery(connection -> {
            String sql = "insert into member(name, board_id) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, boardId);
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("저장에 실패하였습니다.");
            }
            return new Member(generatedKeys.getInt(1), name, boardId);
        });
    }

    private int countByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            String sql = "select count(*) as countMembers from member where board_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("그런 보드 없습니다~");
            }
            return resultSet.getInt("countMembers");
        });
    }

    public void saveAll(List<Member> members, int boardId) {
        for (Member member : members) {
            save(member.getName(), boardId);
        }
    }

    public List<Member> getAllByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            List<Member> members = new ArrayList<>();
            String sql = "select * from member where board_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                members.add(
                        new Member(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("board_id")
                        ));
            }
            return members;
        });
    }
}
