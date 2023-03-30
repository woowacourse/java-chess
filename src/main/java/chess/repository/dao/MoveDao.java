package chess.repository.dao;

import java.util.ArrayList;
import java.util.List;

import chess.repository.entity.MoveEntity;
import chess.repository.entity.MoveEntityBuilder;

public class MoveDao {

	private final JdbcTemplate jdbcTemplate;

	public MoveDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public long save(final MoveEntity moveEntity) {
		final String query = "INSERT INTO move(game_id, source_column, source_row, target_column, target_row) "
			+ "VALUES (?, ?, ?, ?, ?)";
		final List<Object> parameters = List.of(
			moveEntity.getGame_id(),
			moveEntity.getSourceColumn(),
			moveEntity.getSourceRow(),
			moveEntity.getTargetColumn(),
			moveEntity.getTargetRow()
		);
		return jdbcTemplate.executeUpdate(query, parameters);
	}

	public List<MoveEntity> findByGameId(final long gameId) {
		final String query = "SELECT * FROM move WHERE game_id = (?)";
		final List<Object> parameters = List.of(gameId);
		return jdbcTemplate.executeQuery(
			query,
			parameters,
			resultSet -> {
				List<MoveEntity> moveEntities = new ArrayList<>();
				MoveEntityBuilder builder = new MoveEntityBuilder();
				while (resultSet.next()) {
					long id = resultSet.getLong("id");
					int sourceColumn = resultSet.getInt("source_column");
					int sourceRow = resultSet.getInt("source_row");
					int targetColumn = resultSet.getInt("target_column");
					int targetRow = resultSet.getInt("target_row");
					MoveEntity entity = builder.id(id)
						.gameId(gameId)
						.sourceColumn(sourceColumn)
						.sourceRow(sourceRow)
						.targetColumn(targetColumn)
						.targetRow(targetRow)
						.build();
					moveEntities.add(entity);
				}
				return moveEntities;
			});
	}

	public void deleteByGameId(Long gameId) {
		final String query = "DELETE FROM move WHERE id = (?)";
		final List<Object> parameters = List.of(gameId);
		jdbcTemplate.executeUpdateForDelete(query, parameters);
	}
}
