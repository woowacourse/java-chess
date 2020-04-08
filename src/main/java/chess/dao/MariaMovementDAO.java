package chess.dao;

import chess.entity.Movement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ChessConnection.getConnection;

public class MariaMovementDAO implements MovementDAO {
    private final ConnectionProperties connectionProperties;

    public MariaMovementDAO(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Override
    public Movement save(Movement entity) {
        String query = "INSERT INTO MOVEMENT (chessId, sourceKey, targetKey, createdTime) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getChessId());
            preparedStatement.setString(2, entity.getSourceKey());
            preparedStatement.setString(3, entity.getTargetKey());
            LocalDateTime createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(createdTime));

            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            return getMovement(entity, createdTime, generatedKeys);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return entity;
        }


    }

    private Movement getMovement(Movement entity, LocalDateTime createdTime, ResultSet generatedKeys) throws SQLException {
        if (!generatedKeys.next()) {
            return entity;
        }
        long id = generatedKeys.getLong("id");
        generatedKeys.close();

        return new Movement(id, entity, createdTime);
    }

    @Override
    public List<Movement> findAllByChessId(Long chessId) {
        String query = "SELECT * " +
                "FROM MOVEMENT " +
                "WHERE chessId = ?";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, chessId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return collectEntities(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Movement> collectEntities(ResultSet resultSet) throws SQLException {
        List<Movement> movements = new ArrayList<>();
        while (resultSet.next()) {
            movements.add(collectEntity(resultSet));
        }
        return movements;
    }

    private Movement collectEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long chessId = resultSet.getLong("chessId");
        String sourceKey = resultSet.getString("sourceKey");
        String targetKey = resultSet.getString("targetKey");
        LocalDateTime createdTime = resultSet.getTimestamp("createdTime").toLocalDateTime();

        return new Movement(id, chessId, sourceKey, targetKey, createdTime);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM MOVEMENT";
        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
