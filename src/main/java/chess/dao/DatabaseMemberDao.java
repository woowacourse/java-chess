package chess.dao;

import chess.domain.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseMemberDao implements MemberDao {
    private final SQLExecutor executor = SQLExecutor.getInstance();

    @Override
    public Long save(Member member) {
        final String sql = "insert into Member (name) values (?)";
        return executor.insertAndGetGeneratedKey(connection -> {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, member.getName());
            return statement;
        });
    }

    @Override
    public Optional<Member> findById(Long id) {
        final String sql = "select id, name from Member where id = ?";
        return executor.select(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement;
        }, this::serializeMember);
    }

    private Optional<Member> serializeMember(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(new Member(resultSet.getLong("id"), resultSet.getString("name")));
    }

    @Override
    public List<Member> findAll() {
        final String sql = "select id, name from Member";
        return executor.select(connection -> connection.prepareStatement(sql), resultSet -> {
            List<Member> members = new ArrayList<>();
            while (resultSet.next()) {
                members.add(new Member(resultSet.getLong("id"), resultSet.getString("name")));
            }
            return members;
        });
    }

    @Override
    public void deleteById(Long id) {
        final String sql = "delete from Member where id = ?";
        executor.delete(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement;
        });
    }
}
