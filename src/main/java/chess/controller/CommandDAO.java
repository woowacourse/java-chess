package chess.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandDAO {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public void insert(String command) throws SQLException {
        String query = "INSERT INTO command VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, command);
        pstmt.executeUpdate();
    }

    public List<String> selectAll() throws SQLException {
        String query = "SELECT * FROM command";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<String> commands = new ArrayList<>();
        while (rs.next()) {
            commands.add(rs.getString("command"));
        }

        return commands;
    }


    public void deleteAll() throws SQLException {
        String query = "DELETE FROM command";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
