package chess.dao;

import chess.domains.CommandHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandHistoryDao extends ConnectionManager {
    public void createRecord(CommandHistory commandHistory) {
        String query = "INSERT INTO record VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            int number = this.countRecords() + 1;
            pstmt.setInt(1, number);
            pstmt.setString(2, commandHistory.getCommandHistory());
            pstmt.setString(3, commandHistory.getErrorMsg());
            pstmt.setString(4, commandHistory.getSource());
            pstmt.setString(5, commandHistory.getTarget());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private int countRecords() {
        String query = "SELECT COUNT(*) FROM  record;";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            int recordCount = 0;

            if (rs.next()) {
                recordCount = rs.getInt(1);
            }

            return recordCount;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void deleteRecord() {
        String query = "TRUNCATE record";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public List<CommandHistory> readRecords() {
        String query = "SELECT * FROM record";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            List<CommandHistory> commandHistories = new ArrayList<>();

            while (rs.next()) {
                commandHistories.add(new CommandHistory(rs.getString("record"),
                        rs.getString("source"), rs.getString("target"), rs.getString("errorMsg")));
            }
            return commandHistories;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}