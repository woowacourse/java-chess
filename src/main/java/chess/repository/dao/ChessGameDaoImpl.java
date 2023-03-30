package chess.repository.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.repository.entity.MoveDto;

public class ChessGameDaoImpl implements ChessGameDao {

	private final JdbcTemplate jdbcTemplate;

	private ChessGameDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static ChessGameDaoImpl create() {
		return new ChessGameDaoImpl(new JdbcTemplate());
	}

	@Override
	public boolean isLastGameExists() {
		final String query = "SELECT COUNT(*) as cnt FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			parameters,
			resultSet -> {
				while (resultSet.next()) {
					if (resultSet.getInt("cnt") > 0) {
						return true;
					}
				}
				return false;
				}
		);
	}

	@Override
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

	@Override
	public List<MoveDto> findMoves() {
		final String query = "SELECT * FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			parameters,
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
			}
		);
	}

	@Override
	public void deleteAllMoves() {
		final String query = "DELETE FROM move";
		final List<Object> parameters = Collections.emptyList();
		jdbcTemplate.executeUpdate(query, parameters);
	}
}
