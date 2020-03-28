package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.position.Position;

public class Running extends Started {
	public Running(ChessBoard chessBoard) {
		super(chessBoard);
	}

	public Running(ChessBoard chessBoard, Side turn) {
		super(chessBoard, turn);
	}

	@Override
	public State move(Position source, Position target) {
		validateTurn(source, turn);
		chessBoard.move(source, target);

		if (chessBoard.isEnd()) {
			return new End(chessBoard, turn.reverse());
		}
		return new Running(chessBoard, turn.reverse());
	}

	private void validateTurn(Position source, Side turn) {
		if (!chessBoard.isRightTurn(source, turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	@Override
	public boolean isEnd() {
		return false;
	}
}
