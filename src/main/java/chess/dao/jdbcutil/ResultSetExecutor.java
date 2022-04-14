package chess.dao.jdbcutil;

import chess.dao.JdbcException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetExecutor {
    private final ResultSet resultSet;

    public ResultSetExecutor(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public <T> T getFirst(ResultSetFunction<ResultSet, T> entityFunction) {
        try (ResultSet resultSet = this.resultSet) {
            return applyFirst(entityFunction);
        } catch (SQLException e) {
            throw new JdbcException("단일 데이터 조회에 실패했습니다.", e);
        }
    }

    public <T> List<T> getAll(ResultSetFunction<ResultSet, T> entityFunction) {
        try (ResultSet resultSet = this.resultSet) {
            return applyAll(entityFunction);
        } catch (SQLException e) {
            throw new JdbcException("데이터 조회에 실패했습니다.", e);
        }
    }

    private <T> T applyFirst(ResultSetFunction<ResultSet, T> entityFunction) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        return entityFunction.convert(resultSet);
    }

    private <T> List<T> applyAll(ResultSetFunction<ResultSet, T> entityFunction) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            T entity = entityFunction.convert(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}
