package chess.repository;

import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Ready;
import chess.domain.state.Started;

public class StartedGameDAO implements GameDAO{
	private Game game;

	public StartedGameDAO() {
		this.game = new Game(new Started(new Board()));
		game.start();
	}

	@Override
	public Optional<Game> findById(int gameId) {
		return Optional.of(game);
	}

	@Override
	public void update(Game game) {
		this.game = game;
	}
}