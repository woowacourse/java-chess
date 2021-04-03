package chess.domain.dao;

import chess.domain.entity.Movement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MysqlMovementDao implements MovementDao {
    public MysqlMovementDao() {
        String query = "CREATE TABLE IF NOT EXISTS movement ( " +
                "movement_id VARCHAR(36) NOT NULL," +
                "chess_id VARCHAR(36) NOT NULL," +
                "source_position VARCHAR(64) NOT NULL," +
                "target_position VARCHAR(64) NOT NULL," +
                "PRIMARY KEY (movement_id)," +
                "FOREIGN KEY (chess_id) REFERENCES chess(chess_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

        Connection connection = ConnectionUtil.getConnection();
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
        String query = "INSERT INTO movement VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionUtil.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movement.getId());
            preparedStatement.setString(2, movement.getChessId());
            preparedStatement.setString(3, movement.getSourcePosition());
            preparedStatement.setString(4, movement.getTargetPosition());
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
                " WHERE ch.name = ?";
        Connection connection = ConnectionUtil.getConnection();

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
                        resultSet.getString("target_position")
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
        ConnectionUtil.closeConnection(connection);
        preparedStatement.close();
    }
}
