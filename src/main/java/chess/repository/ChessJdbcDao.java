package chess.repository;

import chess.db.JdbcTemplate;
import chess.dto.MoveDto;
import java.util.ArrayList;
import java.util.List;

public class ChessJdbcDao implements ChessDao {
    private final JdbcTemplate jdbcTemplate;

    public ChessJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final MoveDto moveDto) {
        jdbcTemplate.executeUpdate(
                "INSERT INTO move (source, target) VALUES (?, ?)",
                moveDto.getSource(),
                moveDto.getTarget()
        );
    }

    @Override
    public List<MoveDto> findAll() {
        return jdbcTemplate.query("SELECT * FROM move", resultSet -> {
            final List<MoveDto> result = new ArrayList<>();
            while (resultSet.next()) {
                final String source = resultSet.getString("source");
                final String target = resultSet.getString("target");
                result.add(new MoveDto(source, target));
            }
            return result;
        });
    }

    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM move");
    }
}
