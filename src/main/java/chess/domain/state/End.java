package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessStatus;
import chess.domain.position.Position;

public class End extends Started {
	public End(ChessBoard chessBoard) {
		super(chessBoard);
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ChessStatus calculateStatus() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEnd() {
		return true;
	}
}
