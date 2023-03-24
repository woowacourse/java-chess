package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveMapper implements RowMapper<Move> {

    @Override
    public Move create(final ResultSet resultSet) throws SQLException {
        final String source = resultSet.getString("source");
        final String target = resultSet.getString("target");
        return new Move(source, target);
    }
}
