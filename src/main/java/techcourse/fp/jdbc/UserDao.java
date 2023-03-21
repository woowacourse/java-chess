package techcourse.fp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class UserDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void insert(final User user) {
        //쿼리 날림
        String sql = "INSERT INTO user (user_id,name) VALUES (?,?)";
        try (final Connection connection = getConnection();
            final var preparedStatement = connection.prepareStatement(sql)) {
            //setString 통해 위 쿼리문 ? 부분에 변수 넣음
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (final Connection connection = getConnection();
            final var preparedStatement = connection.prepareStatement(sql)) {
            //setString 통해 위 쿼리문 ? 부분에 변수 넣음
            preparedStatement.setString(1, userId);

            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("user_id"),
                    resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateUser(User user){
        String sql = "UPDATE user SET name = ? WHERE user_id = ?";
        try (final Connection connection = getConnection();
            final var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User user){
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (final Connection connection = getConnection();
            final var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
