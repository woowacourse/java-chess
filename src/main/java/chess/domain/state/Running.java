package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.position.Position;

public class Running extends Started {
	public Running(ChessBoard chessBoard, Side turn) {
		super(chessBoard, turn);
	}

	@Override
	public State move(Position source, Position target) {
		try {
			chessBoard.move(source, target, turn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Running(chessBoard, turn);
		}

		if (chessBoard.isEnd()) {
			return new End(chessBoard);
		}
		return new Running(chessBoard, turn.reverse());
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
