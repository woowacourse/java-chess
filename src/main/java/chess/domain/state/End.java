package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.position.Position;

public class End extends Started {
	public End(ChessBoard chessBoard) {
		super(chessBoard);
	}

	public End(ChessBoard chessBoard, Side turn) {
		super(chessBoard, turn);
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ChessStatus createStatus() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public State end() {
		throw new UnsupportedOperationException();
	}
}
