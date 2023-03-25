package chess.domain.dao;

import chess.domain.Piece;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper {

    Piece run(final ResultSet rs) throws SQLException;
    
}
