package db;

import domain.dto.TurnDto;

import java.util.List;

public class TurnDao {
    private static final String TABLE_NAME = "turns";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TurnDto> rowMapper = (resultSet) ->
            new TurnDto(resultSet.getString("color"));

    TurnDao() {
        this(new JdbcTemplate());
    }

    private TurnDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    TurnDto find() {
        final var query = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        final List<TurnDto> turns = jdbcTemplate.find(query, rowMapper);
        if (turns.isEmpty()) {
            throw new IllegalArgumentException("데이터가 없습니다.");
        }
        return turns.get(0);
    }


    void update(final TurnDto turnDto) {
        final String deleteQuery = "DELETE FROM " + TABLE_NAME;
        final String insertQuery = "INSERT INTO " + TABLE_NAME + " (color) VALUES (?)";
        jdbcTemplate.delete(deleteQuery);
        jdbcTemplate.add(insertQuery, turnDto.color());
    }

    public void deleteAll() {
        final String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.delete(query);
    }
}
