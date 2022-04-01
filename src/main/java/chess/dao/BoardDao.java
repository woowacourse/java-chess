package chess.dao;

import chess.util.JdbcTemplate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {

    public Map<String, String> getBoard() {
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement("select * from board");
             ResultSet resultSet = preparedStatement.executeQuery();){

            Map<String, String> board = new HashMap<>();
            while (resultSet.next()) {
                board.put(resultSet.getString(1), resultSet.getString(2));
            }
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePosition(final String position, final String piece) {
        String sql = "update board set piece = ? where position = ?";
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, piece);
            preparedStatement.setString(2, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBatchPositions(final Map<String, String> board) {
        String sql = "update board set piece = ? where position = ?";
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement(sql)){

            for (Entry<String, String> boardEntry : board.entrySet()) {
                preparedStatement.setString(1, boardEntry.getValue());
                preparedStatement.setString(2, boardEntry.getKey());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
