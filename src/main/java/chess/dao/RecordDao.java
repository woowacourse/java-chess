package chess.dao;

import chess.domains.Record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDao extends ConnectionManager {
    public int countRecords() {
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

    public void addRecord(Record record) {
        String query = "INSERT INTO record VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            int number = this.countRecords() + 1;
            pstmt.setInt(1, number);
            pstmt.setString(2, record.getRecord());
            pstmt.setString(3, record.getErrorMsg());
            pstmt.setString(4, record.getSource());
            pstmt.setString(5, record.getTarget());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void clearRecord() {
        String query = "TRUNCATE record";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public List<Record> readRecords() {
        String query = "SELECT * FROM record";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            List<Record> records = new ArrayList<>();

            while (rs.next()) {
                records.add(new Record(rs.getString("record"),
                        rs.getString("source"), rs.getString("target"), rs.getString("errorMsg")));
            }
            return records;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}