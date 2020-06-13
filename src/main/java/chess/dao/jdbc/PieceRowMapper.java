package chess.dao.jdbc;

import chess.domain.dto.PieceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceRowMapper implements RowMapper<PieceDto> {
    @Override
    public PieceDto mapRow(ResultSet rs) throws SQLException {
        rs.next();
        return new PieceDto(
                rs.getString("team"),
                rs.getString("name"),
                rs.getString("position")
        );
    }

    @Override
    public List<PieceDto> mapRows(ResultSet rs) throws SQLException {
        List<PieceDto> results = new ArrayList<>();
        while (rs.next()) {
            PieceDto pieceDto = new PieceDto(
                    rs.getString("team"),
                    rs.getString("name"),
                    rs.getString("position"));
            results.add(pieceDto);
        }
        return results;
    }
}
