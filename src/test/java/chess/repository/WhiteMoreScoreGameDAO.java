package chess.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.state.KingCatchFinished;
import chess.domain.state.SuspendFinished;

public class WhiteMoreScoreGameDAO implements GameDAO{

	@Override
	public Optional<Game> findById(int gameId) {
		Map<Position, Piece> maps = new HashMap<>();
		maps.put(Position.of("a3"), PieceFactory.of("b"));
		maps.put(Position.of("a5"), PieceFactory.of("k"));
		maps.put(Position.of("a7"), PieceFactory.of("P"));
		maps.put(Position.of("b7"), PieceFactory.of("K"));
		Game game = new Game(new SuspendFinished(new Board(maps), Team.WHITE));
		return Optional.of(game);
	}

	@Override
	public void update(Game game) {

	}
}