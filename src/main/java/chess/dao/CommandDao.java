package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.DBConnection.getConnection;

public class CommandDao {
    public void insert(String command) {
        String query = "INSERT INTO command VALUES (?)";

        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, command);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insert Error");
        }
    }

    public List<String> selectAll() {
        String query = "SELECT * FROM command";
        List<String> commands = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                commands.add(rs.getString("command"));
            }
        } catch (SQLException e) {
            System.err.println("select All Error");
        }
        return commands;
    }


    public void deleteAll() {
        String query = "DELETE FROM command";
        try (Connection connection = getConnection(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("select All Error");
        }
    }
}
