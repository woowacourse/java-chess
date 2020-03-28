package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.position.Position;

public class End extends Starting {
	public End(ChessBoard chessBoard) {
		super(chessBoard);
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double status(Side side) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEnd() {
		return true;
	}
}
