package chess.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import chess.domain.game.ChessGame;
import chess.domain.game.state.State;
import chess.domain.game.state.StateFactory;
import chess.dto.BoardDto;

public class JDBCChessGameDao implements ChessGameDao {
	private JDBCTemplate jdbcTemplate;

	public JDBCChessGameDao() {
		this.jdbcTemplate = new JDBCTemplate();
	}

	@Override
	public int create() throws SQLException {
		PreparedStatementSetter setter = pstmt -> {
			pstmt.setString(1, "READY");
		};

		RowMapper<Integer> mapper = rs -> {
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		};

		return jdbcTemplate.executeUpdate("INSERT INTO chess_game(state) VALUES (?)", setter, mapper);
	}

	@Override
	public List<Integer> findAll() throws SQLException {
		PreparedStatementSetter setter = pstmt -> {
		};

		RowMapper<List<Integer>> mapper = rs -> {
			List<Integer> ids = new ArrayList<>();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
			return ids;
		};

		return jdbcTemplate.executeQuery("SELECT id FROM chess_game", setter, mapper);
	}

	@Override
	public Optional<ChessGame> findById(int id) throws SQLException {
		PreparedStatementSetter setter = pstmt -> {
			pstmt.setInt(1, id);
		};

		RowMapper<Optional<ChessGame>> mapper = rs -> {
			if (rs.next()) {
				State state = StateFactory.valueOf(rs.getString(2)).create(rs.getString(3), rs.getString(4));
				return Optional.of(new ChessGame(state));
			}
			return Optional.empty();
		};

		return jdbcTemplate.executeQuery("SELECT * FROM chess_game WHERE id = ?", setter, mapper);
	}

	@Override

	public void updateById(int id, ChessGame chessGame) throws SQLException {
		PreparedStatementSetter setter = pstmt -> {
			pstmt.setString(1, chessGame.getState().toString());
			pstmt.setString(2, String.join("", new BoardDto(chessGame.board()).getBoard()));
			pstmt.setString(3, String.valueOf(chessGame.turn()));
			pstmt.setInt(4, id);
		};

		RowMapper<Void> mapper = rs -> null;

		jdbcTemplate.executeUpdate("UPDATE chess_game SET state = ?, board = ?, turn = ? WHERE id = ?", setter, mapper);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatementSetter setter = pstmt -> {
			pstmt.setInt(1, id);
		};

		RowMapper<Void> mapper = rs -> null;

		jdbcTemplate.executeUpdate("DELETE FROM chess_game WHERE id = ?", setter, mapper);
	}
}
