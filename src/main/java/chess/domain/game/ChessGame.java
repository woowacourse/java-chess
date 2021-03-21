package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessGame {
	public static final String TURN_MESSAGE = "%s의 차례입니다.";
	public static final String NO_MOVEMENT_ERROR = "현재 위치와 같은 곳으로 이동할 수 없습니다.";
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;

	private final ChessBoard chessBoard;
	private Color turn;

	public ChessGame(ChessBoard chessBoard, Color turn) {
		this.chessBoard = chessBoard;
		this.turn = turn;
	}

	public void start() {
		chessBoard.initBoard();
	}

	public void run(List<String> input) {
		String source = input.get(SOURCE_INDEX);
		String target = input.get(TARGET_INDEX);
		validateMovement(source, target);

		Piece sourcePiece = chessBoard.getPiece(Position.of(source));
		validateTurn(sourcePiece);

		chessBoard.move(source, target);
		turn = turn.getOppositeColor();
	}

	private void validateMovement(final String source, final String target) {
		if (source.equals(target)) {
			throw new IllegalArgumentException(NO_MOVEMENT_ERROR);
		}
	}

	private void validateTurn(Piece sourcePiece) {
		if (!sourcePiece.isSameColor(turn)) {
			throw new IllegalArgumentException(String.format(TURN_MESSAGE, turn.name()));
		}
	}

	public boolean isOngoing() {
		return chessBoard.bothKingsAlive();
	}

	public Result result() {
		double blackScore = chessBoard.getScore(Color.WHITE);
		double whiteScore = chessBoard.getScore(Color.BLACK);
		return new Result(blackScore, whiteScore);
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}
}
