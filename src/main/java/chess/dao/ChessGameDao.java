package chess.dao;

import java.sql.SQLException;
import java.util.Map;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.BoardRepository;
import chess.domain.state.Playing;

public class ChessGameDao {
	public ChessGame findById(int id) throws SQLException {
		MoveDao moveDao = new MoveDao();
		Map<String, String> moves = moveDao.findMovesByGameId(id);
		ChessGame game = new ChessGame(new Playing(BoardRepository.create(), new Turn(Team.WHITE)));
		for (Map.Entry<String, String> move : moves.entrySet()) {
			game.move(Position.of(move.getKey()), Position.of(move.getValue()));
		}
		return game;
	}
}
