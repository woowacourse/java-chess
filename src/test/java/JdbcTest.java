import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    @Test
    void insert() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
                final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES(?, ?)")
        ) {
            preparedStatement.setString(1, "emil");
            preparedStatement.setString(2, "emil");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void select() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, "emil");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
                final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name = ? WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, "emilll");
            preparedStatement.setString(2, "emil");
            preparedStatement.executeUpdate();
            select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void delete() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "root");
                final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, "emil");
            preparedStatement.executeUpdate();
            select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
