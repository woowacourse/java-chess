package chess.repository.dao;

import java.util.List;

import chess.repository.entity.UserEntity;
import chess.repository.entity.UserEntityBuilder;

public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	public UserDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public long save(final UserEntity user) {
		final String query = "INSERT INTO user(name) VALUES(?)";
		String name = user.getName();
		final List<Object> parameters = List.of(name);
		return jdbcTemplate.executeUpdate(query, parameters);
	}

	public UserEntity findByName(final String name) {
		final String query = "SELECT * FROM user WHERE name = (?)";
		final List<Object> parameters = List.of(name);
		return jdbcTemplate.executeQuery(
			query,
			parameters,
			resultSet -> {
				UserEntityBuilder builder = new UserEntityBuilder();
				if (resultSet.next()) {
					long id = resultSet.getLong("id");
					String userName = resultSet.getString("name");
					return builder.id(id)
						.name(userName)
						.build();
				}
				return builder.build();
			});
	}

	public void deleteById(Long userId) {
		final String query = "DELETE FROM user WHERE id = (?)";
		final List<Object> parameters = List.of(userId);
		jdbcTemplate.executeUpdateForDelete(query, parameters);
	}
}
