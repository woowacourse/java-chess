package chess.dao;

import chess.util.JdbcTemplate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
}
