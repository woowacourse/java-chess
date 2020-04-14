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
import chess.domain.state.Started;

public class BlackKingCatchedGameDAO implements GameDAO{

	@Override
	public Optional<Game> findById(int gameId) {
		Map<Position, Piece> maps = new HashMap<>();
		maps.put(Position.of("a3"), PieceFactory.of("b"));
		maps.put(Position.of("a5"), PieceFactory.of("k"));
		maps.put(Position.of("a7"), PieceFactory.of("Q"));
		Game game = new Game(new KingCatchFinished(new Board(maps), Team.WHITE));
		return Optional.of(game);
	}

	@Override
	public void update(Game game) {

	}
}