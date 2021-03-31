package chess.dao;

import static chess.dao.setting.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQuery {

    public static Long insert(String query, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query,RETURN_GENERATED_KEYS);
        setParameters(preparedStatement, parameters);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            return null;
        }
        return generatedKeys.getLong(1);
    }

    public static ResultSet select(String query, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeQuery();
    }

    public static int updateOrRemove(String query, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeUpdate();
    }

    private static void setParameters(PreparedStatement pstmt, Object[] parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            int parameterOrder = i + 1;
            pstmt.setObject(parameterOrder, parameters[i]);
        }
    }
}
