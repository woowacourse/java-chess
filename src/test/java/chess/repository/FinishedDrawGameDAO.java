package chess.repository;

import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Started;

public class FinishedDrawGameDAO implements GameDAO{

	@Override
	public Optional<Game> findById(int gameId) {
		Game game = new Game(new Started(new Board()));
		game.start();
		game.end();
		return Optional.of(game);
	}

	@Override
	public void update(Game game) {

	}
}