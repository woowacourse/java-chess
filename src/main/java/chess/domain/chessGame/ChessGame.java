package chess.domain.chessGame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.dto.ChessBoardDto;
import chess.domain.chessGame.dto.ChessStatusDto;
import chess.domain.chessGame.gameState.ChessEndState;
import chess.domain.chessGame.gameState.GameState;
import chess.domain.chessGame.gameState.KingCaughtState;
import chess.domain.chessGame.gameState.WhiteTurnState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import chess.util.ChessBoardRenderer;

public class ChessGame {

	private static final boolean KING_CAUGHT_STATE = true;
	private static final boolean NOT_KING_CAUGHT_STATE = false;

	private final ChessBoard chessBoard;
	private GameState gameState;

	private ChessGame(ChessBoard chessBoard, GameState gameState) {
		this.chessBoard = chessBoard;
		this.gameState = gameState;
	}

	public static ChessGame from(ChessBoard chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");
		return new ChessGame(chessBoard, new WhiteTurnState());
	}

	public void move(ChessCommand chessCommand) {
		Objects.requireNonNull(chessCommand, "체스 명령이 null입니다.");
		Position sourcePosition = chessCommand.getSourcePosition();
		Position targetPosition = chessCommand.getTargetPosition();

		boolean isKingOnTargetPosition = chessBoard.isKingOn(targetPosition);

		moveChessPieceFrom(sourcePosition, targetPosition);
		shiftGameStatusBy(isKingOnTargetPosition);
	}

	private void moveChessPieceFrom(Position sourcePosition, Position targetPosition) {
		checkChessPieceExistOn(sourcePosition);
		checkCorrectChessTurn(sourcePosition);
		checkLeapable(sourcePosition, targetPosition);
		checkMovableOrCatchable(sourcePosition, targetPosition);
		chessBoard.moveChessPiece(sourcePosition, targetPosition);
	}

	private void checkChessPieceExistOn(Position sourcePosition) {
		if (!chessBoard.isChessPieceOn(sourcePosition)) {
			throw new IllegalArgumentException("이동할 체스 피스가 존재하지 않습니다.");
		}
	}

	private void checkCorrectChessTurn(final Position sourcePosition) {
		if (!chessBoard.isSamePieceColorOn(sourcePosition, gameState.getPieceColor())) {
			throw new IllegalArgumentException("순서에 맞지 않은 말을 이동하였습니다.");
		}
	}

	private void checkLeapable(Position sourcePosition, Position targetPosition) {
		if (!chessBoard.isLeapableChessPieceOn(sourcePosition)) {
			chessBoard.checkChessPieceExistInRoute(sourcePosition, targetPosition);
		}
	}

	private void checkMovableOrCatchable(Position sourcePosition, Position targetPosition) {
		if (!chessBoard.isChessPieceOn(targetPosition)) {
			chessBoard.checkCanMove(sourcePosition, targetPosition);
			return;
		}
		chessBoard.checkCanCatch(sourcePosition, targetPosition);
	}

	private void shiftGameStatusBy(boolean isKingOnTargetPosition) {
		if (isKingOnTargetPosition) {
			gameState = gameState.shiftEndState(KING_CAUGHT_STATE);
			return;
		}
		gameState = gameState.shiftNextTurnState();
	}

	public double status(ChessCommand chessCommand) {
		Objects.requireNonNull(chessCommand, "체스 명령이 null입니다.");
		ChessStatus chessStatus = chessBoard.calculateStatus();
		return chessStatus.getStatusOf(chessCommand.getStatusPieceColor());
	}

	public void end() {
		gameState = gameState.shiftEndState(NOT_KING_CAUGHT_STATE);
	}

	public boolean isEndState() {
		return gameState instanceof ChessEndState;
	}

	public boolean isKingCaught() {
		return gameState instanceof KingCaughtState;
	}

	public PieceColor getCurrentTurnColor() {
		return this.gameState.getPieceColor();
	}

	public List<String> getRenderedChessBoard() {
		return ChessBoardRenderer.render(chessBoard);
	}

	public ChessBoardDto getChessBoardDto() {
		return chessBoard.getChessBoardDto();
	}

	public List<ChessStatusDto> getChessStatusDtos() {
		ChessStatus chessStatus = chessBoard.calculateStatus();

		return Arrays.stream(PieceColor.values())
			.map(pieceColor -> ChessStatusDto.of(pieceColor, chessStatus.getStatusOf(pieceColor)))
			.collect(Collectors.toList());
	}

}
