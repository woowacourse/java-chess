package chess.repository;

import chess.db.JdbcTemplate;
import chess.dto.MoveDto;
import java.util.ArrayList;
import java.util.List;

public class GameJdbcDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final MoveDto moveDto, final int roomId) {
        jdbcTemplate.executeUpdate(
                "INSERT INTO move (source, target, room_id) VALUES (?, ?, ?)",
                moveDto.getSource(),
                moveDto.getTarget(),
                roomId
        );
    }

    @Override
    public List<MoveDto> findAllByRoomId(final int roomId) {
        return jdbcTemplate.query("SELECT * FROM move where room_id = ?", resultSet -> {
            final List<MoveDto> result = new ArrayList<>();
            while (resultSet.next()) {
                final String source = resultSet.getString("source");
                final String target = resultSet.getString("target");
                result.add(new MoveDto(source, target));
            }
            return result;
        }, roomId);
    }

    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM move");
    }
}
