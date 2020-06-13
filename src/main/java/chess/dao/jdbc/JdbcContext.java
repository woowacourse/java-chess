package chess.dao.jdbc;

import chess.dao.StatementStrategy;
import chess.dao.error.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcContext<T> {
    private final DataSource dataSource;
    private final RowMapper<T> rowMapper;

    public JdbcContext(DataSource dataSource, RowMapper<T> rowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
    }

    public void updateWithStatementStrategy(StatementStrategy statement) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = dataSource.getConnection();
            ps = statement.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public T queryObject(StatementStrategy statement) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();
            ps = statement.makePreparedStatement(c);
            rs = ps.executeQuery();
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public List<T> queryObjects(StatementStrategy statementStrategy) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();
            ps = statementStrategy.makePreparedStatement(c);
            rs = ps.executeQuery();
            return rowMapper.mapRows(rs);
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }

    }
}
