package db;

import domain.board.position.Position;
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
        final var query = "INSERT INTO movement (source,  target, shape, color) values(?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movement.source().toString());
            preparedStatement.setString(2, movement.target().toString());
            preparedStatement.setString(3, movement.shape());
            preparedStatement.setString(4, movement.color());
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
                        Position.from(resultSet.getString("source")),
                        Position.from(resultSet.getString("target")),
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
                        Position.from(resultSet.getString("source")),
                        Position.from(resultSet.getString("target")),
                        resultSet.getString("shape"),
                        resultSet.getString("color")
                ));
            }
            return movements;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE from movement";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
