package chess.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.utils.ConnectionManager.getConnection;

public abstract class SelectJdbcTemplate {

    public Object select(String query) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()
        ) {
            return mapRow(rs);
        }
    }

    public abstract void setParameters(PreparedStatement statement) throws SQLException;

    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
