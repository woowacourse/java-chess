package db;

import domain.dto.PieceDto;

import java.util.List;

public class PieceDao {
    private static final String TABLE_NAME = "pieces";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PieceDto> rowMapper = (resultSet) -> new PieceDto(
            resultSet.getString("board_file"),
            resultSet.getString("board_rank"),
            resultSet.getString("color"),
            resultSet.getString("type")
    );

    PieceDao() {
        this(new JdbcTemplate());
    }

    private PieceDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void add(final PieceDto piece) {
        final String query = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?)";
        jdbcTemplate.add(query, piece.boardFile(), piece.boardRank(), piece.color(), piece.type());
    }

    PieceDto findOne(final String file, final String rank) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE board_file = ? and board_rank = ? limit 1";
        final List<PieceDto> pieces = jdbcTemplate.find(query, rowMapper, file, rank);
        if (pieces.size() == 0) {
            throw new IllegalArgumentException("데이터가 없습니다.");
        }
        return pieces.get(0);
    }

    List<PieceDto> findAll() {
        final String query = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.find(query, rowMapper);
    }

    void deleteAll() {
        final String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.delete(query);
    }

    boolean hasRecords() {
        final String query = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        final List<PieceDto> pieces = jdbcTemplate.find(query, rowMapper);
        return pieces.size() != 0;
    }
}
