package chess.dao;

import chess.config.DataSource;
import chess.config.JdbcTemplate;
import chess.config.RowMapper;
import chess.dto.RoomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoomDao {
    private JdbcTemplate jdbcTemplate;

    private RoomDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static RoomDao from(final DataSource dataSource) {
        return new RoomDao(dataSource);
    }

    public int add() {
        String sql = "INSERT INTO room () VALUES()";
        return jdbcTemplate.executeUpdate(sql);
    }

    public Optional<RoomDto> findById(final long id) {
        String sql = "SELECT * FROM room WHERE id = ?";
        List<Object> params = Collections.singletonList(id);

        RoomDto roomDto = jdbcTemplate.executeQuery(sql, params, rs -> {
            if (rs.next()) {
                RoomDto roomDto1 = new RoomDto();
                roomDto1.setId(rs.getLong("id"));
                roomDto1.setStatus(rs.getBoolean("status"));
                roomDto1.setWinner(rs.getString("winner"));
                return roomDto1;
            }
            return null;
        });

        return Optional.ofNullable(roomDto);
    }

    public List<RoomDto> findAllByStatus(final boolean status) {
        String sql = "SELECT * FROM room WHERE status = ?";
        List<Object> params = Collections.singletonList(status);

        return jdbcTemplate.executeQuery(sql, params, rs -> {
            List<RoomDto> roomDtos = new ArrayList<>();
            while (rs.next()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setId(rs.getLong("id"));
                roomDto.setStatus(rs.getBoolean("status"));
                roomDto.setWinner(rs.getString("winner"));
                roomDtos.add(roomDto);
            }
            return roomDtos;
        });
    }

    public int updateStatus(final long id, final String winner) {
        String sql = "UPDATE room SET status = TRUE, winner = ? WHERE id = ?";
        List<Object> params = Arrays.asList(winner, id);
        return jdbcTemplate.executeUpdate(sql, params);
    }

    public Optional<Long> getLatestId() {
        String sql = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
        Long id = jdbcTemplate.executeQuery(sql, rs -> rs.next() ? rs.getLong("id") : null);
        return Optional.ofNullable(id);
    }

    public int deleteAll() {
        String sql = "DELETE from room";
        return jdbcTemplate.executeUpdate(sql);
    }
}
