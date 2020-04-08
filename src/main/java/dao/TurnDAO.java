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
        PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, Team.WHITE.toString());
        pstmt.executeUpdate();
    }

    public void changeTurn() throws SQLException {
        String querySelect = "SELECT * from turn";
        String queryUpdate = "UPDATE turn SET team=?";
        PreparedStatement pstmtSelect = JDBCConnection.getConnection().prepareStatement(querySelect);
        PreparedStatement pstmtUpdate = JDBCConnection.getConnection().prepareStatement(queryUpdate);
        ResultSet rs = pstmtSelect.executeQuery();
        rs.next();

        Team team = Team.findTeam(rs.getObject(1).toString());

        pstmtUpdate.setString(1, Team.opposite(team).toString());
        pstmtUpdate.executeUpdate();
    }

    public void delete() throws SQLException {
        String query = "delete from turn";
        PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public Team getTurn() throws SQLException {
        String query = "SELECT * from turn";
        PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        return Team.findTeam(rs.getObject(1).toString());
    }
}
