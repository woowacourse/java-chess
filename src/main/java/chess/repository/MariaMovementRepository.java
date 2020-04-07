package chess.repository;

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

import static chess.repository.ChessConnection.closeConnection;
import static chess.repository.ChessConnection.getConnection;

public class MariaMovementRepository implements MovementRepository {
    private final ConnectionProperties connectionProperties;

    public MariaMovementRepository(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Override
    public Movement save(Movement entity) throws SQLException {
        String query = "INSERT INTO MOVEMENT (chessId, sourceKey, targetKey, createdTime) VALUES (?, ?, ?, ?)";

        Connection connection = getConnection(connectionProperties);
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, entity.getChessId());
        preparedStatement.setString(2, entity.getSourceKey());
        preparedStatement.setString(3, entity.getTargetKey());
        LocalDateTime createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(createdTime));

        preparedStatement.execute();
        closeConnection(connection);

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (!generatedKeys.next()) {
            return entity;
        }

        return new Movement(generatedKeys.getLong("id"), entity, createdTime);
    }

    @Override
    public List<Movement> findAllByChessId(Long chessId) throws SQLException {
        String query = "SELECT * " +
                "FROM MOVEMENT " +
                "WHERE chessId = ?";
        Connection connection = getConnection(connectionProperties);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, chessId);
        ResultSet resultSet = preparedStatement.executeQuery();
        closeConnection(connection);

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
    public void deleteAll() throws SQLException {
        String query = "DELETE FROM MOVEMENT";
        Connection connection = getConnection(connectionProperties);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        closeConnection(connection);
    }
}
