package chess.dao;

import chess.model.member.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChessMemberDao implements MemberDao<Member> {

    private final ConnectionManager connectionManager;

    public ChessMemberDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Member> getAllByRoomId(int roomId) {
        return connectionManager.executeQuery(connection -> {
            List<Member> members = new ArrayList<>();
            String sql = "SELECT * FROM member WHERE room_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                members.add(
                        new Member(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("room_id")
                        ));
            }
            return members;
        });
    }

    @Override
    public Member save(String name, int roomId) {
        return connectionManager.executeQuery(connection -> {
            String sql = "INSERT INTO member(name, room_id) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, roomId);
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("저장에 실패하였습니다: " + name);
            }
            return new Member(generatedKeys.getInt(1), name, roomId);
        });
    }

    @Override
    public void saveAll(List<Member> members, int roomId) {
        for (Member member : members) {
            save(member.getName(), roomId);
        }
    }
}
