package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JDBCTemplate {
    private static class JDBCTemplateLazyHolder {
        private static final JDBCTemplate INSTANCE = new JDBCTemplate();
    }

    public static JDBCTemplate getInstance() {
        return JDBCTemplateLazyHolder.INSTANCE;
    }

    public void executeUpdate(String query, List<Object> queryValues) {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, query, queryValues)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Map<String, Object>> executeQuery(String query) {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            return createResult(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Map<String, Object>> executeQuery(String query, List<Object> queryValues) {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, query, queryValues);
             ResultSet rs = statement.executeQuery()) {
            return createResult(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    private List<Map<String, Object>> createResult(ResultSet rs) throws SQLException {
        List<Map<String, Object>> endResult = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> result = offerResult(rs);
            endResult.add(result);
        }

        return endResult;
    }

    private Map<String, Object> offerResult(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        ResultSetMetaData reMetaData = rs.getMetaData();

        for (int i = 1; i <= reMetaData.getColumnCount(); i++) {
            String columnName = reMetaData.getColumnName(i);
            result.put(columnName, rs.getObject(columnName));
        }

        return result;
    }

    private PreparedStatement createPreparedStatement(Connection connection, String query, List<Object> queryValues) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 0; i < queryValues.size(); i++) {
            statement.setObject(i + 1, queryValues.get(i));
        }

        return statement;
    }
}
