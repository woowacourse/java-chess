package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    interface ResultSetExtractor<T> {
        T extractData(ResultSet rs) throws SQLException;
    }

    public <T> T queryForObject(String sql, ResultSetExtractor<T> extractor, Object... args) {
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            prepareStatement(pstmt, args);
            return extractToObject(extractor, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void prepareStatement(PreparedStatement pstmt, Object[] args) throws SQLException {
        for (int i = 1; i <= args.length; i++) {
            pstmt.setString(i, args[i - 1].toString());
        }
    }

    private <T> T extractToObject(ResultSetExtractor<T> extractor, PreparedStatement pstmt) throws SQLException {
        try (ResultSet resultSet = pstmt.executeQuery()) {
            resultSet.next();
            return extractor.extractData(resultSet);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... args) {
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            prepareStatement(pstmt, args);
            return extract(mapper, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> extract(RowMapper<T> mapper, PreparedStatement pstmt) throws SQLException {
        try (ResultSet resultSet = pstmt.executeQuery()) {
            return extractToList(mapper, resultSet);
        }
    }

    private <T> List<T> extractToList(RowMapper<T> mapper, ResultSet resultSet) throws SQLException {
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(mapper.map(resultSet));
        }
        return result;
    }

    public void update(String sql, Object... args) {
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            prepareStatement(pstmt, args);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(String sql, Object... args) {
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            prepareStatement(pstmt, args);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost:13306/chess?useSSL=false&serverTimezone=UTC", "user",
                            "password");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return con;
    }
}
