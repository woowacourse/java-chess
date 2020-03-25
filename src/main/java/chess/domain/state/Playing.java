package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public class Playing implements State {
	private Board board;

	public Playing(Board board) {
		this.board = board;
		board.initialize();
	}

	@Override
	public State start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State end() {
		return new Finished();
	}

	@Override
	public State move(Position source, Position target) {
		return this;
	}

	@Override
	public Board board() {
		return board;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
