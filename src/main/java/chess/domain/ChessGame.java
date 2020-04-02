package chess.domain;

import chess.domain.position.Position;

public class ChessGame {
	private ChessBoard board;
	private Side turn;
	private boolean isEnd;

	public ChessGame(ChessBoard board, Side turn) {
		this(board, turn, false);
	}

	public ChessGame(ChessBoard board, Side turn, boolean isEnd) {
		this.board = board;
		this.turn = turn;
		this.isEnd = isEnd;
	}

	public static ChessGame start() {
		return new ChessGame(ChessBoardFactory.create(), Side.WHITE);
	}

	public ChessGame move(Position source, Position target) {
		validateTurn(source);
		board.move(source, target);

		return new ChessGame(board, turn.reverse());
	}

	private void validateTurn(Position source) {
		if (board.isRightTurn(source, turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	public double status(Side side) {
		return board.calculateScore(side);
	}

	public boolean isEnd() {
		return board.isEnd() || isEnd;
	}

	public ChessGame end() {
		return new ChessGame(board, turn, true);
	}

	public ChessBoard getBoard() {
		return board;
	}
}
