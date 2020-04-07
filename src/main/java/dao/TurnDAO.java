package dao;

import domain.team.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void start() throws SQLException {
        String query = "INSERT INTO turn VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, Team.WHITE.toString());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void changeTurn() throws SQLException {
        String querySelect = "SELECT * from turn";
        String queryUpdate = "UPDATE turn SET team=?";
        PreparedStatement pstmtSelect = getConnection().prepareStatement(querySelect);
        PreparedStatement pstmtUpdate = getConnection().prepareStatement(queryUpdate);
        ResultSet rs = pstmtSelect.executeQuery();
        rs.next();

        Team team = Team.findTeam(rs.getObject(1).toString());

        pstmtUpdate.setString(1, Team.opposite(team).toString());
        pstmtUpdate.executeUpdate();
        pstmtSelect.close();
        pstmtUpdate.close();
    }

    public void delete() throws SQLException {
        String query = "delete from turn";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public Team getTurn() throws SQLException {
        String query = "SELECT * from turn";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        Team team = Team.findTeam(rs.getObject(1).toString());
        return team;
    }
}
