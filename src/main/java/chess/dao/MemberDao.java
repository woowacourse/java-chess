package chess.dao;

import chess.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private final ConnectionManager connectionManager;

    public MemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<Member> getAllByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            List<Member> members = new ArrayList<>();
            String sql = "SELECT * FROM member WHERE board_id=?";
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

    public Member save(String name, int boardId) {
        return connectionManager.executeQuery(connection -> {
            String sql = "INSERT INTO member(name, board_id) VALUES(?, ?)";
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

    public void saveAll(List<Member> members, int boardId) {
        for (Member member : members) {
            save(member.getName(), boardId);
        }
    }
}
