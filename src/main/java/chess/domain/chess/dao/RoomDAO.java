package chess.domain.chess.dao;

import chess.domain.chess.game.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void add() throws SQLException {
        String query = "INSERT INTO room (team) VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Team.WHITE.name());

        pstmt.executeUpdate();
    }

    public void update(int id, Team team) throws SQLException {
        String query = "UPDATE room SET team = ? WHERE id = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, team.name());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    public Team select(int roomId) throws SQLException {
        String query = "SELECT team FROM room WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new SQLException();

        return Team.getTeamByName(resultSet.getString("team"));
    }

    public List<Integer> getIds() throws SQLException {
        String query = "SELECT id FROM room ORDER BY id ASC LIMIT 100 OFFSET 0";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<Integer> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        return ids;
    }

    public int getRecentId() throws SQLException {
        String query = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new SQLException();

        return resultSet.getInt("id");
    }

}
