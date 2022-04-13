package chess.dao.connect;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RsFunction<T> {

    T apply(final ResultSet resultSet) throws SQLException;
}
