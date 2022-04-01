package chess.dao;

import chess.util.JdbcTemplate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    public String getCurrentTurn() {
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement("select * from turn");
             ResultSet resultSet = preparedStatement.executeQuery();){
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
