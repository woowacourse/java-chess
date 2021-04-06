package chess.dao;

import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    interface Mapper<T, R, U> {
        U apply(T t, R r) throws SQLException;
    }

    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String query, Mapper<ResultSet, Integer, T> mapper, String... parameters) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            setParameters(pstmt, parameters);

            try(ResultSet resultSet = pstmt.executeQuery()) {
                return createResultWithMapper(resultSet, mapper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    private <T> List<T> createResultWithMapper(ResultSet rs, Mapper<ResultSet, Integer, T> mapper) throws SQLException {
        List<T> result = new ArrayList<>();

        for (int i = 0; rs.next(); i++) {
            result.add(mapper.apply(rs, i));
        }

        return result;
    }

    public void update(String query, String... parameters) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            setParameters(pstmt, parameters);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e);
        }
    }

    private void setParameters(PreparedStatement pstmt, String... parameters) throws SQLException {
        for (int i = 1; i <= parameters.length; i++) {
            pstmt.setString(i, parameters[i - 1]);
        }
    }

}
