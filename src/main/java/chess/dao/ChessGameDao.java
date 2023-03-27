package chess.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.dto.MoveDto;

public class ChessGameDao {

	private final JdbcTemplate jdbcTemplate;

	private ChessGameDao(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static ChessGameDao create() {
		return new ChessGameDao(new JdbcTemplate());
	}

	public boolean isLastGameExists() {
		final String query = "SELECT COUNT(*) as cnt FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			resultSet -> {
				while (resultSet.next()) {
					if (resultSet.getInt("cnt") > 0) {
						return true;
					}
				}
				return false;
				},
			parameters
		);
	}

	public void saveMove(final MoveDto moveDto) {
		final String query = "INSERT INTO move(source_column, source_row, target_column, target_row) "
			+ "VALUES (?, ?, ?, ?)";
		final List<Object> parameters = List.of(
			moveDto.getSourceColumn(),
			moveDto.getSourceRow(),
			moveDto.getTargetColumn(),
			moveDto.getTargetRow()
		);
		jdbcTemplate.executeUpdate(query, parameters);
	}

	public List<MoveDto> loadMoves() {
		final String query = "SELECT * FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			resultSet -> {
				List<MoveDto> moves = new ArrayList<>();
				while (resultSet.next()) {
					int sourceColumn = resultSet.getInt("source_column");
					int sourceRow = resultSet.getInt("source_row");
					int targetColumn = resultSet.getInt("target_column");
					int targetRow = resultSet.getInt("target_row");
					moves.add(new MoveDto(sourceColumn, sourceRow, targetColumn, targetRow));
				}
				return moves;
			},
			parameters
		);
	}

	public void deleteMoves() {
		final String query = "DELETE FROM move";
		final List<Object> parameters = Collections.emptyList();
		jdbcTemplate.executeUpdate(query, parameters);
	}
}
