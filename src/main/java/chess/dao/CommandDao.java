package chess.dao;

import chess.config.DataSource;
import chess.config.JdbcTemplate;
import chess.dto.CommandDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandDao {
    private static final int DEFAULT_START_ROUND = 1;

    private JdbcTemplate jdbcTemplate;

    private CommandDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static CommandDao from(final DataSource dataSource) {
        return new CommandDao(dataSource);
    }

    public void add(final CommandDto commandDto) {
        String sql = "INSERT INTO command (origin, target, round, room_id) VALUES(?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(commandDto.getOrigin());
        params.add(commandDto.getTarget());
        params.add(commandDto.getRound());
        params.add(commandDto.getRoom_id());

        jdbcTemplate.executeUpdate(sql, params);
    }

    public List<CommandDto> findByRoomId(final long roomId) {
        String sql = "SELECT * FROM command WHERE room_id = ? ORDER BY round";
        List<Object> params = Collections.singletonList(roomId);

        return jdbcTemplate.executeQuery(sql, params, rs -> {
            ArrayList<CommandDto> commandDtos = new ArrayList<>();
            while (rs.next()) {
                CommandDto commandDto = new CommandDto();
                commandDto.setOrigin(rs.getString("origin"));
                commandDto.setTarget(rs.getString("target"));
                commandDto.setRound(rs.getLong("round"));
                commandDtos.add(commandDto);
            }
            return commandDtos;
        });
    }

    public long findLatestRoundByRoomId(final long roomId) {
        String sql = "SELECT round FROM command WHERE room_id = ? ORDER BY round DESC LIMIT 1";
        List<Object> params = Collections.singletonList(roomId);
        return jdbcTemplate.executeQuery(sql, params, rs -> rs.next() ? rs.getLong("round") : DEFAULT_START_ROUND);
    }
}
