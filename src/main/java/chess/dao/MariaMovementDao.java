package chess.dao;

import chess.entity.Movement;
import chess.handler.exception.SqlExecuteException;

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

public class MariaMovementDao implements MovementDao {
    private final ConnectionProperties connectionProperties;

    public MariaMovementDao(ConnectionProperties connectionProperties) {
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

            return getMovement(entity, createdTime, preparedStatement);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private Movement getMovement(Movement entity, LocalDateTime createdTime, PreparedStatement preparedStatement) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (!generatedKeys.next()) {
                throw new SqlExecuteException("엔티티 저장 실패");
            }
            long id = generatedKeys.getLong("id");
            return new Movement(id, entity, createdTime);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }

    }

    @Override
    public List<Movement> findAllByChessId(Long chessId) {
        String query = "SELECT * " +
                "FROM MOVEMENT " +
                "WHERE chessId = ?";

        try (Connection connection = getConnection(connectionProperties);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, chessId);

            return collectEntities(preparedStatement);
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
    }

    private List<Movement> collectEntities(PreparedStatement preparedStatement) {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Movement> movements = new ArrayList<>();
            while (resultSet.next()) {
                movements.add(collectEntity(resultSet));
            }
            return movements;
        } catch (SQLException e) {
            throw new SqlExecuteException(e.getMessage());
        }
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
            throw new SqlExecuteException(e.getMessage());
        }
    }
}
