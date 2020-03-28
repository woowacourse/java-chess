package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.position.Position;

public abstract class Starting implements State {
	public ChessBoard chessBoard;
	public Side turn;

	public Starting(ChessBoard chessBoard) {
		this(chessBoard, Side.WHITE);
	}

	public Starting(ChessBoard chessBoard, Side side) {
		this.chessBoard = chessBoard;
		this.turn = side;
	}

	@Override
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	@Override
	public abstract State move(Position source, Position target);

	@Override
	public State end() {
		return new End(chessBoard);
	}
}
