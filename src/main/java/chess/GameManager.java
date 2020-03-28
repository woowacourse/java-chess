package chess;

import java.util.Map;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.type.Piece;

public class GameManager {
	private final ChessBoard chessBoard;
	private GameState gameState;

	public GameManager() {
		this.gameState = GameState.RUNNING;
		this.chessBoard = new ChessBoard();
	}

	public boolean isRunning() {
		return gameState.isGameRunning();
	}

	public void movePiece(Location now, Location destination) {
		if (chessBoard.canMove(now, destination)) {
			chessBoard.move(now, destination);
		}
		gameState = gameState.of(chessBoard.hasTwoKings());
	}

	public Map<Location, Piece> getBoard() {
		return chessBoard.getBoard();
	}
}
