package chess.repository.dao;

import java.util.ArrayList;
import java.util.List;

import chess.repository.entity.UserGameEntity;
import chess.repository.entity.UserGameEntityBuilder;

public class UserGameDao {

	private final JdbcTemplate jdbcTemplate;

	public UserGameDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public long save(final UserGameEntity userGameEntity) {
		final String query = "INSERT INTO user_game(user_id, game_id) VALUES(?, ?)";
		long userId = userGameEntity.getUserId();
		long gameId = userGameEntity.getGameId();
		final List<Object> parameters = List.of(userId, gameId);
		return jdbcTemplate.executeUpdate(query, parameters);
	}

	public List<UserGameEntity> findByUserId1OrUserId2(final long userId1, final long userId2) {
		final String query = "SELECT * FROM user_game WHERE user_id IN (?, ?) ";
		final List<Object> parameters = List.of(userId1, userId2);
		return jdbcTemplate.executeQuery(
			query,
			parameters,
			resultSet -> {
				List<UserGameEntity> userGameEntities = new ArrayList<>();
				UserGameEntityBuilder builder = new UserGameEntityBuilder();
				while (resultSet.next()) {
					long id = resultSet.getLong("id");
					long userId = resultSet.getLong("user_id");
					long gameId = resultSet.getLong("game_id");
					UserGameEntity entity = builder.id(id)
						.userId(userId)
						.gameId(gameId)
						.build();
					userGameEntities.add(entity);
				}
				return userGameEntities;
			});
	}

	public void deleteById(Long userGameId) {
		final String query = "DELETE FROM user_game WHERE id = (?)";
		final List<Object> parameters = List.of(userGameId);
		jdbcTemplate.executeUpdateForDelete(query, parameters);
	}
}
