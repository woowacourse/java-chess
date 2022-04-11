package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDaoImpl implements StateDao {

    private final DataSource dataSource;

    public StateDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(String name) {
        final String sql = "insert into state (name) values (?)";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String find() {
        final String sql = "select name from state";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("[ERROR] 데이터가 없습니다.");
    }

    @Override
    public void delete() {
        final String sql = "delete from state";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String now, String next) {
        final String sql = "update state set name = ? where name = ?";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, next);
            statement.setString(2, now);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
