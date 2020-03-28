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
		chessBoard.move(source, target, turn);

		if (chessBoard.isEnd()) {
			return new End(chessBoard, turn.reverse());
		}
		return new Running(chessBoard, turn.reverse());
	}

	@Override
	public boolean isEnd() {
		return false;
	}
}
