package chess.dao.jdbc;

import chess.domain.dto.PieceDto;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class ColumnMapRowMapper implements RowMapper<List<Object>> {
    @Override
    public List<Object> mapRow(ResultSet rs) throws SQLException {
        List<Object> results = new ArrayList<>();
        while (rs.next()) {
            PieceDto pieceDto = new PieceDto(
                    rs.getString("id"),
                    rs.getString("team"),
                    rs.getString("name"),
                    rs.getString("position")
            );
            results.add(pieceDto);
        }
        return results;
    }


}
