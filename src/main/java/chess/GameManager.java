package chess;

import java.util.Map;

import chess.Score.Score;
import chess.board.ChessBoard;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.type.Piece;
import chess.team.Team;

public class GameManager {
	private final ChessBoard chessBoard;
	private GameState gameState;

	public GameManager() {
		this.chessBoard = new ChessBoard();
		this.gameState = GameState.of(chessBoard.hasTwoKings());
	}

	public boolean isRunning() {
		return gameState.isGameRunning();
	}

	public void movePiece(Location now, Location destination) {
		if (chessBoard.canMove(now, destination)) {
			chessBoard.move(now, destination);
		}
		gameState = GameState.of(chessBoard.hasTwoKings());
	}

	public Map<Location, Piece> getBoard() {
		return chessBoard.getBoard();
	}

	public Score calculateScore(Team team) {
		return chessBoard.calculateScore(team);
	}
}
