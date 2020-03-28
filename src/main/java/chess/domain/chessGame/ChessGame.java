package chess.domain.chessGame;

import java.util.List;
import java.util.Objects;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessGame.gameState.ChessEndState;
import chess.domain.chessGame.gameState.GameState;
import chess.domain.chessGame.gameState.KingCaughtState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import chess.util.ChessBoardRenderer;

public class ChessGame {

	private static final int SOURCE_INDEX = 0;
	private static final int TARGET_INDEX = 1;
	private static final boolean KING_CAUGHT_STATE = true;
	private static final boolean END_STATE = false;

	private final ChessBoard chessBoard;
	private GameState gameState;

	public ChessGame(ChessBoard chessBoard, GameState gameState) {
		this.chessBoard = chessBoard;
		this.gameState = gameState;
	}

	public void move(List<String> arguments) {
		validate(arguments);
		chessBoard.move(Position.of(arguments.get(SOURCE_INDEX)), Position.of(arguments.get(TARGET_INDEX)));

		if (chessBoard.isKingCaughtAt(Position.of(arguments.get(TARGET_INDEX)))) {
			gameState = gameState.shiftEndState(KING_CAUGHT_STATE);
			return;
		}
		gameState = gameState.shiftNextTurnState();
	}

	public double status(List<String> arguments) {
		validate(arguments);
		return chessBoard.calculateScoreOf(gameState.getTurnPieceColor());
	}

	private void validate(List<String> arguments) {
		if (Objects.isNull(arguments) || arguments.isEmpty()) {
			throw new IllegalArgumentException("유효하지 않은 명령어 인자입니다.");
		}
	}

	public void end() {
		gameState = gameState.shiftEndState(END_STATE);
	}

	public boolean isEndState() {
		return gameState instanceof ChessEndState;
	}

	public boolean isKingCaught() {
		return gameState instanceof KingCaughtState;
	}

	public PieceColor getCurrentTurnColor() {
		return this.gameState.getTurnPieceColor();
	}

	public List<String> getRenderedChessBoard() {
		return ChessBoardRenderer.render(chessBoard);
	}

}
