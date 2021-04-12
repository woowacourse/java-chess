package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.DBConnection.getConnection;

public class CommandDao {
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
