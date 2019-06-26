package chess.dao;

import chess.config.DataSource;
import chess.config.JdbcTemplate;
import chess.dto.CommandDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandDao {
    private static final int DEFAULT_START_TURN = 1;

    private JdbcTemplate jdbcTemplate;

    private CommandDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static CommandDao from(final DataSource dataSource) {
        return new CommandDao(dataSource);
    }

    public void add(final CommandDto commandDto) {
        String sql = "INSERT INTO command (origin, target, turn, room_id) VALUES(?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(commandDto.getOrigin());
        params.add(commandDto.getTarget());
        params.add(commandDto.getTurn());
        params.add(commandDto.getRoomId());

        jdbcTemplate.executeUpdate(sql, params);
    }

    public List<CommandDto> findByRoomId(final long roomId) {
        String sql = "SELECT * FROM command WHERE room_id = ? ORDER BY turn";
        List<Object> params = Collections.singletonList(roomId);

        return jdbcTemplate.executeQuery(sql, params, rs -> {
            ArrayList<CommandDto> commandDtos = new ArrayList<>();
            while (rs.next()) {
                CommandDto commandDto = new CommandDto();
                commandDto.setOrigin(rs.getString("origin"));
                commandDto.setTarget(rs.getString("target"));
                commandDto.setTurn(rs.getLong("turn"));
                commandDtos.add(commandDto);
            }
            return commandDtos;
        });
    }

    public long findLatestRoundByRoomId(final long roomId) {
        String sql = "SELECT turn FROM command WHERE room_id = ? ORDER BY turn DESC LIMIT 1";
        List<Object> params = Collections.singletonList(roomId);
        return jdbcTemplate.executeQuery(sql, params, rs -> rs.next() ? rs.getLong("turn") : DEFAULT_START_TURN);
    }
}
