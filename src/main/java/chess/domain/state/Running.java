package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.position.Position;

public class Running extends Starting {
	public Running(ChessBoard chessBoard, Side turn) {
		super(chessBoard, turn);
	}

	@Override
	public State move(Position source, Position target) {
		validateTurn(source);
		chessBoard.move(source, target);

		if (chessBoard.isEnd()) {
			return new End(chessBoard);
		}
		return new Running(chessBoard, turn.reverse());
	}

	private void validateTurn(Position source) {
		if (chessBoard.isRightTurn(source, turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	@Override
	public double status(Side side) {
		return chessBoard.calculateScore(side);
	}

	@Override
	public boolean isEnd() {
		return false;
	}
}
