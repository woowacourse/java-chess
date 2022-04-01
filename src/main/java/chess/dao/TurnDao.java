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

    public void updateTurn(final String currentTurn, final String previousTurn) {
        String sql = "update turn set team = ? where team = ?";
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, currentTurn);
            preparedStatement.setString(2, previousTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
