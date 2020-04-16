package chess.model.repository.template;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetMapper<T> {

    T setRow(ResultSet rs) throws SQLException;
}
