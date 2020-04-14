package chess.repository;

import java.util.Optional;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.state.Ready;

public class ReadyGameDAO implements GameDAO{

	@Override
	public Optional<Game> findById(int gameId) {
		return Optional.of(new Game(new Ready(new Board())));
	}

	@Override
	public void update(Game game) {

	}
}