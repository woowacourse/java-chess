package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveFindAllStrategy implements QueryStrategy {

    @Override
    public void save(final PreparedStatement preparedStatement) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public <T> List<T> findAll(final ResultSet resultSet, final RowMapper<T> mapper) throws SQLException {

        final List<T> results = new ArrayList<>();
        while (resultSet.next()) {
            final T t = mapper.create(resultSet);
            results.add(t);
        }

        return results;
    }
}
