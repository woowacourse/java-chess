package chess.dao.jdbc;

import chess.domain.dto.PieceDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceRowMapper implements RowMapper<PieceDto> {
    @Override
    public PieceDto mapRow(ResultSet rs) throws SQLException {
        return new PieceDto(
                rs.getString("team"),
                rs.getString("name"),
                rs.getString("position")
        );
    }
}
