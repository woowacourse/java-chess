package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper {
    <T> List<T> mapRow(ResultSet resultSet) throws SQLException;
}
