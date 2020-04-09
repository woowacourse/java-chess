package chess.repository;

import chess.domain.game.Game;
import chess.domain.state.GameStateFactory;
import chess.utils.jdbc.JDBCTemplate;

public class MySQLGameDAO implements GameDAO {
	private final JDBCTemplate jdbcTemplate;

	public MySQLGameDAO(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Game findById(int userId) {
		String query = "SELECT * FROM game WHERE id = ?";
		return jdbcTemplate.executeQuery(query, rs -> new Game(GameStateFactory.of(
			rs.getString("state"),
			rs.getString("turn"),
			rs.getString("board"))
		), userId);
	}

	public void update(Game game) {
		String query = "UPDATE game SET state=?, turn=?, board=? WHERE id=?";
		jdbcTemplate.executeUpdate(query, game.getStateType().getState(), game.getTurn().name(),
			game.getBoard().getAsString(), game.getId());
	}
}
