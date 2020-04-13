package chess.repository;

import java.util.Objects;
import java.util.Optional;

import chess.domain.game.Game;
import chess.domain.state.GameStateFactory;
import chess.utils.jdbc.JDBCTemplate;

public class JDBCGameDAO implements GameDAO {
	private final JDBCTemplate jdbcTemplate;

	public JDBCGameDAO(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = Objects.requireNonNull(jdbcTemplate);
	}

	@Override
	public Optional<Game> findById(int gameId) {
		String query = "SELECT * FROM game WHERE id = ?";
		return jdbcTemplate.executeQuery(query, rs ->
			new Game(GameStateFactory.of(
				rs.getString("state"),
				rs.getString("turn"),
				rs.getString("board")
			)), pstmt -> pstmt.setInt(1, gameId));
	}

	@Override
	public void update(Game game) {
		String query = "UPDATE game SET state=?, turn=?, board=? WHERE id=?";
		jdbcTemplate.executeUpdate(query, preparedStatement -> {
			preparedStatement.setString(1, game.getStateType());
			preparedStatement.setString(2, game.getTurn().name());
			preparedStatement.setString(3, game.getBoard().getAsString());
			preparedStatement.setInt(4, game.getId());
		});
	}
}
