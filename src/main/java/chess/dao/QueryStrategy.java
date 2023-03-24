package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryStrategy {

    void save(final PreparedStatement preparedStatement) throws SQLException;

    <T> List<T> findAll(final ResultSet resultSet, final RowMapper<T> rowMapper) throws SQLException;
}
