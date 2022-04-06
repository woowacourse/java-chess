package chess.web.dao.camp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CampDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "camp";
    private static final String CURRENT_CAMP = "currentCamp";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String findCurrentCamp() {
        final String sql = "" + "SELECT currentCamp" + "  FROM " + TABLE;
        try (final PreparedStatement statement = getConnection().prepareStatement(
            sql); final ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString(CURRENT_CAMP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(final String camp) {
        final String sql = ""
            + "UPDATE " + TABLE
            + " SET currentCamp = ?";
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, camp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
