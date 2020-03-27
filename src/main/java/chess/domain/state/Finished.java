package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class Finished implements State {
	@Override
	public State start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State end() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Board board() {
		return Board.create();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public Score score(Color color) {
		throw new UnsupportedOperationException();
	}
}
