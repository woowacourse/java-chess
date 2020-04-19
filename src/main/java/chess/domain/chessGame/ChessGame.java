package chess.domain.chessGame;

import java.util.Objects;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessGame.gameState.GameState;
import chess.domain.chessGame.gameState.WhiteTurnState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public class ChessGame {

	private static final boolean KING_CAUGHT_STATE = true;
	private static final boolean NOT_KING_CAUGHT_STATE = false;

	private final ChessBoard chessBoard;
	private GameState gameState;

	private ChessGame(final ChessBoard chessBoard, final GameState gameState) {
		this.chessBoard = chessBoard;
		this.gameState = gameState;
	}

	public static ChessGame from(final ChessBoard chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");

		return new ChessGame(chessBoard, new WhiteTurnState());
	}

	public void move(final ChessCommand chessCommand) {
		Objects.requireNonNull(chessCommand, "체스 명령이 null입니다.");

		final Position sourcePosition = chessCommand.getSourcePosition();
		final Position targetPosition = chessCommand.getTargetPosition();

		final boolean isKingOnTargetPosition = chessBoard.isKingOn(targetPosition);

		moveChessPieceFrom(sourcePosition, targetPosition);
		shiftGameStatusBy(isKingOnTargetPosition);
	}

	private void moveChessPieceFrom(final Position sourcePosition, final Position targetPosition) {
		checkChessPieceExistOn(sourcePosition);
		checkCorrectChessTurn(sourcePosition);
		checkLeapable(sourcePosition, targetPosition);
		checkMovableOrCatchable(sourcePosition, targetPosition);
		chessBoard.moveChessPiece(sourcePosition, targetPosition);
	}

	private void checkChessPieceExistOn(final Position sourcePosition) {
		if (!chessBoard.isChessPieceOn(sourcePosition)) {
			throw new IllegalArgumentException("이동할 체스 피스가 존재하지 않습니다.");
		}
	}

	private void checkCorrectChessTurn(final Position sourcePosition) {
		if (!chessBoard.isSamePieceColorOn(sourcePosition, gameState.getPieceColor())) {
			throw new IllegalArgumentException("순서에 맞지 않은 말을 이동하였습니다.");
		}
	}

	private void checkLeapable(final Position sourcePosition, final Position targetPosition) {
		if (!chessBoard.isLeapableChessPieceOn(sourcePosition)) {
			chessBoard.checkChessPieceExistInRoute(sourcePosition, targetPosition);
		}
	}

	private void checkMovableOrCatchable(final Position sourcePosition, final Position targetPosition) {
		if (!chessBoard.isChessPieceOn(targetPosition)) {
			chessBoard.checkCanMove(sourcePosition, targetPosition);
			return;
		}
		chessBoard.checkCanCatch(sourcePosition, targetPosition);
	}

	private void shiftGameStatusBy(final boolean isKingOnTargetPosition) {
		if (isKingOnTargetPosition) {
			gameState = gameState.shiftEndState(KING_CAUGHT_STATE);
			return;
		}
		gameState = gameState.shiftNextTurnState();
	}

	public double status(final ChessCommand chessCommand) {
		Objects.requireNonNull(chessCommand, "체스 명령이 null입니다.");
		final ChessStatus chessStatus = chessBoard.calculateStatus();
		return chessStatus.getStatusOf(chessCommand.getStatusPieceColor());
	}

	public void end() {
		gameState = gameState.shiftEndState(NOT_KING_CAUGHT_STATE);
	}

	public boolean isEndState() {
		return gameState.isEndState();
	}

	public boolean isKingCaught() {
		return gameState.isKingCaughtState();
	}

	public ChessStatus getChessGameStatus() {
		return chessBoard.calculateStatus();
	}

	public PieceColor getCurrentPieceColor() {
		return this.gameState.getPieceColor();
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}

}
