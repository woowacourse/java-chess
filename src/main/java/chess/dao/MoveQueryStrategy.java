package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface MoveQueryStrategy {

    void save(final PreparedStatement preparedStatement) throws SQLException;

    List<Move> findAll(final PreparedStatement preparedStatement) throws SQLException;
}
