package chess.dao;

import static chess.dao.Connector.getConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    public int findGameId() {
        final String sql = "select id from game order by id DESC LIMIT 1";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return loadGameIdFromDB(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private int loadGameIdFromDB(final PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            return checkExistId(resultSet);
        }
    }

    private int checkExistId(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new RuntimeException();
        }
        return resultSet.getInt("id");
    }

    public String findGameState() {
        final String sql = "select * from game order by id DESC LIMIT 1";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return loadStateFromDB(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String loadStateFromDB(final PreparedStatement statement) throws SQLException {
        try (final ResultSet resultSet = statement.executeQuery()) {
            return checkExistState(resultSet);
        }
    }

    private String checkExistState(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new RuntimeException();
        }
        return resultSet.getString("state");
    }

    public void save(final String state) {
        final String sql = "insert into game (state) values (?)";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, state);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(final String state, int gameId) {
        final String sql = "update game set state = (?) where id = (?)";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, state);
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete() {
        final String sql = "delete from game";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(int id) {
        final String sql = "delete from game where id = (?)";

        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
