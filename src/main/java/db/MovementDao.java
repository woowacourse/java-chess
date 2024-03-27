package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MovementDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
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

    public void createMovement(final Movement movement) {
        final var query = "INSERT INTO movement (source_file, source_rank, target_file,target_rank, shape, color) values(?, ?, ?, ?,?,?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movement.sourceFile());
            preparedStatement.setString(2, movement.sourceRank());
            preparedStatement.setString(3, movement.targetFile());
            preparedStatement.setString(4, movement.targetRank());
            preparedStatement.setString(5, movement.shape());
            preparedStatement.setString(6, movement.color());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movement findByMovementId(final String id) {
        final var query = "SELECT * FROM movement WHERE movement_id = ? ORDER BY movement_id";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Movement(
                        resultSet.getString("source_file"),
                        resultSet.getString("source_rank"),
                        resultSet.getString("target_file"),
                        resultSet.getString("target_rank"),
                        resultSet.getString("shape"),
                        resultSet.getString("color")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Movement> findAll() {
        final var query = "SELECT * FROM movement";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            final List<Movement> movements = new ArrayList<>();
            while (resultSet.next()) {
                movements.add(new Movement(
                        resultSet.getString("source_file"),
                        resultSet.getString("source_rank"),
                        resultSet.getString("target_file"),
                        resultSet.getString("target_rank"),
                        resultSet.getString("shape"),
                        resultSet.getString("color")
                ));
            }
            return movements;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

//
//    public boolean deleteByUserId(final String userId) {
//        final var query = "DELETE from user WHERE user_id = ?";
//        try (final var connection = getConnection();
//             final var preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, userId);
//            final int i = preparedStatement.executeUpdate();
//            return i == 1;
//        } catch (final SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean updateByUserId(final String userId, final String name) {
//        final var query = "UPDATE user SET name = ? WHERE user_id = ?";
//        try (final var connection = getConnection();
//             final var preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, userId);
//            final int i = preparedStatement.executeUpdate();
//            return i == 1;
//        } catch (final SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
