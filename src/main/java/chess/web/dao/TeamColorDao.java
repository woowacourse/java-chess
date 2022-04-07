package chess.web.dao;

import chess.domain.piece.vo.TeamColor;
import chess.web.dbmanager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamColorDao {

    private static final int CURRENT_TURN_INDEX = 1;

    private final DBManager dbManager;

    public TeamColorDao(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void update(TeamColor teamColor) {
        try {
            Connection connection = dbManager.getConnection();
            String sql = "update teamColor SET currentTurn = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(CURRENT_TURN_INDEX, teamColor.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TeamColor findCurrentTurn() {
        TeamColor currentTurn = TeamColor.EMPTY;
        Connection connection = dbManager.getConnection();
        try {
            String sql = "select currentTurn from teamColor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            currentTurn = TeamColor.valueOf(resultSet.getString("currentTurn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentTurn;
    }
}
