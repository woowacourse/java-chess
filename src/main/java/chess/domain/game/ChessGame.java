package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Position sourcePosition = Position.of(input.get(SOURCE_INDEX));
		Position targetPosition = Position.of(input.get(TARGET_INDEX));
		validateMovement(sourcePosition, targetPosition);
		validateTurn(sourcePosition);

		chessBoard.move(sourcePosition, targetPosition);
		turn = turn.getOppositeColor();
	}

	private void validateMovement(Position sourcePosition, Position targetPosition) {
		if (sourcePosition.equals(targetPosition)) {
			throw new IllegalArgumentException(NO_MOVEMENT_ERROR);
		}
	}

	private void validateTurn(Position position) {
		Piece sourcePiece = chessBoard.getPiece(position);
		if (!sourcePiece.isSameColor(turn)) {
			throw new IllegalArgumentException(String.format(TURN_MESSAGE, turn.name()));
		}
	}

	public boolean isOngoing() {
		return chessBoard.isOngoing();
	}

	public Result result() {
		Map<Color, Double> resultStatistics = createResultStatistics();
		return new Result(resultStatistics);
	}

	private Map<Color, Double> createResultStatistics() {
		double blackScore = chessBoard.getScore(Color.WHITE);
		double whiteScore = chessBoard.getScore(Color.BLACK);

		Map<Color, Double> resultStatistics = new HashMap<>();
		resultStatistics.put(Color.BLACK, blackScore);
		resultStatistics.put(Color.WHITE, whiteScore);
		return resultStatistics;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}
}
