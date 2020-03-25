package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public class Ready implements State {
	@Override
	public State start() {
		return new Playing(new Board());
	}

	@Override
	public State end() {
		return new Finished();
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Board board() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
