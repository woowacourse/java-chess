package chess.dao;

import chess.domain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUserDao extends Dao implements UserDao {

    @Override
    public void save(User user) {
        final Connection connection = getConnection();
        final String sql = "insert users(user_id, user_name) values(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public User findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select * from users where user_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("user_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findAll() {
        final Connection connection = getConnection();
        final String sql = "select * from users";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            final List<User> findUsers = new ArrayList<>();
            while (resultSet.next()) {
                final User findUser = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("user_name")
                );
                findUsers.add(findUser);
            }
            return findUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void updateById(User user) {
        final Connection connection = getConnection();
        final String sql = "update users set user_name = ? where user_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(String id) {
        final Connection connection = getConnection();
        final String sql = "delete from users where user_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAll() {
        final Connection connection = getConnection();
        final String sql = "delete from users";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
