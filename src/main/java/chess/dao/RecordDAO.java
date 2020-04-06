package chess.dao;

import chess.domains.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "board"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "hwangbo"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public int countRecords() throws SQLException {
        int recordCount = 0;
        String query = "SELECT COUNT(*) FROM  record;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            recordCount = rs.getInt(1);
        }
        closeConnection(getConnection());
        return recordCount;
    }

    public void addRecord(Record record) throws SQLException {
        int number = this.countRecords() + 1;

        String query = "INSERT INTO record VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, number);
        pstmt.setString(2, record.getRecord());
        pstmt.setString(3, record.getErrorMsg());
        pstmt.executeUpdate();

        closeConnection(getConnection());
    }

    public void clearRecord() throws SQLException {
        String query = "TRUNCATE record";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public List<Record> readRecords() throws SQLException {
        String query = "SELECT * FROM record";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<Record> records = new ArrayList<>();

        while (rs.next()) {
            records.add(new Record(rs.getString("record"), rs.getString("errorMsg")));
        }
        closeConnection(getConnection());
        return records;
    }
}