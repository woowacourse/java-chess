package dao;

import domain.team.Team;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDAO {

    private static TurnDAO turnDAO = new TurnDAO();

    private TurnDAO() {
    }

    public static TurnDAO getInstance() {
        return turnDAO;
    }

    public void start() throws SQLException {
        String query = "INSERT INTO turn VALUES (?)";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
            pstmt.setString(1, Team.WHITE.toString());
            pstmt.executeUpdate();
        }
    }

    public void changeTurn(Team team) throws SQLException {
        String queryUpdate = "UPDATE turn SET team=?";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(queryUpdate)) {
            pstmt.setString(1, team.toString());
            pstmt.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        String query = "delete from turn";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }

    public Team getTurn() throws SQLException {
        String query = "SELECT * from turn";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String team = rs.getObject(1).toString();
            return Team.findTeam(team);
        }
    }
}
