package chess.domain.dao;

import chess.domain.entity.Movement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBMovementDao implements MovementDao {
    private final ConnectionPool connectionPool;

    public DBMovementDao() {
        this.connectionPool = CustomConnectionPool.create();

        String query = "CREATE TABLE IF NOT EXISTS movement ( " +
                "movement_id VARCHAR(36) NOT NULL," +
                "chess_id VARCHAR(36) NOT NULL," +
                "source_position VARCHAR(64) NOT NULL," +
                "target_position VARCHAR(64) NOT NULL," +
                "created_date TIMESTAMP," +
                "PRIMARY KEY (movement_id)," +
                "FOREIGN KEY (chess_id) REFERENCES chess(chess_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            closeResources(connection, preparedStatement);

        } catch (SQLException e) {
            System.err.println("movement 테이블 생성오류" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void save(final Movement movement) {
        String query = "INSERT INTO movement VALUES (?, ?, ?, ?, ?)";

        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movement.getId());
            preparedStatement.setString(2, movement.getChessId());
            preparedStatement.setString(3, movement.getSourcePosition());
            preparedStatement.setString(4, movement.getTargetPosition());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(movement.getCreatedDate()));
            preparedStatement.executeUpdate();

            closeResources(connection, preparedStatement);
        } catch (SQLException e) {
            System.err.println("movement 저장 오류" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Movement> findByChessName(final String name) {
        String query = "SELECT * FROM movement as mv" +
                " JOIN chess as ch on mv.chess_id = ch.chess_id" +
                " WHERE ch.name = ?" +
                " ORDER BY mv.created_date";

        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Movement> movements = new ArrayList<>();
            while (resultSet.next()) {
                Movement movement = new Movement(
                        resultSet.getString("movement_id"),
                        resultSet.getString("chess_id"),
                        resultSet.getString("source_position"),
                        resultSet.getString("target_position"),
                        resultSet.getTimestamp("created_date").toLocalDateTime()
                );
                movements.add(movement);
            }

            closeResources(connection, preparedStatement);
            resultSet.close();
            return movements;

        } catch (SQLException e) {
            System.err.println("movement 저장 오류" + e.getMessage());
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        connectionPool.releaseConnection(connection);
        preparedStatement.close();
    }
}
