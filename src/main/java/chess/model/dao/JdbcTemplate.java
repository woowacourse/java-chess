package chess.model.dao;

public interface JdbcTemplate {

    void executeUpdate(final String query, final Object... parameters);

    <T> T executeUpdate(String query, RowMapper<T> rowMapper, Object... parameters);

    <T> T executeQuery(final String query, final RowMapper<T> rowMapper, final Object... parameters);
}
