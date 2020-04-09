package chess.dao;

import chess.domains.Record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDao extends Dao {
    public int countRecords() {
        try {
            int recordCount = 0;
            String query = "SELECT COUNT(*) FROM  record;";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                recordCount = rs.getInt(1);
            }
            closeConnection(getConnection());
            return recordCount;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void addRecord(Record record) {
        try {
            int number = this.countRecords() + 1;

            String query = "INSERT INTO record VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, number);
            pstmt.setString(2, record.getRecord());
            pstmt.setString(3, record.getErrorMsg());
            pstmt.setString(4, record.getSource());
            pstmt.setString(5, record.getTarget());
            pstmt.executeUpdate();

            closeConnection(getConnection());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void clearRecord() {
        try {
            String query = "TRUNCATE record";
            PreparedStatement pstmt = getConnection().prepareStatement(query);

            pstmt.executeUpdate();
            closeConnection(getConnection());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public List<Record> readRecords() {
        try {
            String query = "SELECT * FROM record";
            PreparedStatement pstmt = getConnection().prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            List<Record> records = new ArrayList<>();

            while (rs.next()) {
                records.add(new Record(rs.getString("record"),
                        rs.getString("source"), rs.getString("target"), rs.getString("errorMsg")));
            }
            closeConnection(getConnection());
            return records;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}