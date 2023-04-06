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
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void save(String id) {
        final var query = "INSERT INTO board (id) VALUES (?)";
        try (final var connection = getConnection()) {
            assert connection != null;
            try (final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (final SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException(ExceptionMessages.ALREADY_EXISTING_ID);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMovement(String id, Movement movement) {
        List<Movement> status = findStatusById(id);
        status.add(movement);

        StringBuilder stringBuilder = new StringBuilder();
        status.forEach(move -> {
            stringBuilder.append(move.getStartingSymbol());
            stringBuilder.append(move.getDestinationSymbol());
        });

        final var query = "UPDATE board SET status = ? WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stringBuilder.toString());
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        }
        catch (DataTruncation e) {
            throw new IllegalArgumentException(ExceptionMessages.MAX_MOVEMENT_COUNT);
        }
        catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Movement> findStatusById(String id) {
        final var query = "SELECT * FROM board WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                List<Movement> movements = new ArrayList<>();
                String status = resultSet.getString("status");
                for (int i = 0; i < status.length(); i += 4) {
                    String movement = status.substring(i, Math.min(i + 4, status.length()));
                    Point startingPoint = new Point(
                            File.findBySymbol(movement.substring(0, 1)),
                            Rank.findBySymbol(movement.substring(1, 2))
                    );
                    Point destinationPoint = new Point(
                            File.findBySymbol(movement.substring(2, 3)),
                            Rank.findBySymbol(movement.substring(3, 4))
                    );
                    movements.add(new Movement(startingPoint, destinationPoint));
                }
                return movements;
            }
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_GAME_ID);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(String id) {
        if (!isGameExisting(id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_DELETABLE_GAME_ID);
        }

        final var query = "DELETE FROM board WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isGameExisting(String id) {
        final var query = "SELECT * FROM board WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

