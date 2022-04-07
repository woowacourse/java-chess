package chess.dao;

import chess.util.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDaoImpl implements TurnDao {

    private final Connection connection = JdbcConnector.getConnection();

    @Override
    public void update(String nowTurn, String nextTurn) {
        String sql = "update turn set turn = ? where turn = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nextTurn);
            preparedStatement.setString(2, nowTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTurn() {
        final String sql = "select * from turn";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return resultSet.getString("turn");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset() {
        removeAll("turn");
        final String sql = "insert into turn value white";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeAll(String name) {
        String sql = "truncate table " + name;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
