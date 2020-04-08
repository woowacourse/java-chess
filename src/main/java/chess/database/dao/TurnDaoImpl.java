package chess.database.dao;

import chess.database.JDBCTemplate;
import chess.domain.piece.Color;

public class TurnDaoImpl implements TurnDao {
	JDBCTemplate jdbcTemplate;

	public TurnDaoImpl(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(String turn) {
		String query = "INSERT INTO turn VALUES (?)";
		jdbcTemplate.update(query, preparedStatement -> preparedStatement.setString(1, turn));
	}

	@Override
	public void update(String turn) {
		String query = "UPDATE turn SET current_turn = (?)";
		jdbcTemplate.update(query, preparedStatement -> preparedStatement.setString(1, turn));
	}

	@Override
	public Color getTurn() {
		String query = "SELECT * FROM turn";
		return jdbcTemplate.query(query, (resultSet) -> {
			resultSet.next();
			return Color.of(resultSet.getString(1));
		});
	}

	@Override
	public void deleteTurn() {
		String query = "DELETE FROM turn";
		jdbcTemplate.update(query);
	}
}

