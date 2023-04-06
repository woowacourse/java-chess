package dao;

import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import domain.util.ExceptionMessages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class BoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String id) {
        String query = "INSERT INTO board (id) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(ExceptionMessages.ALREADY_EXISTING_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMovement(String id, Movement movement) {
        String movementStatus = makeMovementStatus(id, movement);
        final var query = "UPDATE board SET status = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movementStatus);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        }
        catch (DataTruncation e) {
            throw new IllegalArgumentException(ExceptionMessages.MAX_MOVEMENT_COUNT);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeMovementStatus(String id, Movement movement) {
        List<Movement> status = findStatusById(id);
        status.add(movement);

        StringBuilder stringBuilder = new StringBuilder();
        status.forEach(move -> {
            stringBuilder.append(move.getStartingSymbol());
            stringBuilder.append(move.getDestinationSymbol());
        });

        return stringBuilder.toString();
    }

    public List<Movement> findStatusById(String id) {
        String query = "SELECT * FROM board WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getMovements(resultSet);
            }
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_GAME_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Movement> getMovements(ResultSet resultSet) throws SQLException {
        List<Movement> movements = new ArrayList<>();
        String status = resultSet.getString("status");
        for (int index = 0; index < status.length(); index += 4) {
            Movement movement = getMovement(status, index);
            movements.add(movement);
        }
        return movements;
    }

    private static Movement getMovement(String movements, int index) {
        String movement = movements.substring(index, Math.min(index + 4, movements.length()));
        Point startingPoint = new Point(
                File.findBySymbol(movement.substring(0, 1)),
                Rank.findBySymbol(movement.substring(1, 2))
        );
        Point destinationPoint = new Point(
                File.findBySymbol(movement.substring(2, 3)),
                Rank.findBySymbol(movement.substring(3, 4))
        );
        return new Movement(startingPoint, destinationPoint);
    }

    public void deleteById(String id) {
        if (!isGameExisting(id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_DELETABLE_GAME_ID);
        }
        String query = "DELETE FROM board WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isGameExisting(String id) {
        String query = "SELECT * FROM board WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

