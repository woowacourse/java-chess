package chess;

import java.util.Map;
import java.util.stream.Collectors;

import chess.Score.Score;
import chess.board.ChessBoard;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.team.Team;

public class GameManager {
	private static final int COLUMN_PAWN_COUNT = 2;
	private final ChessBoard chessBoard;
	private GameState gameState;

	public GameManager(Map<Location, Piece> pieces) {
		this.chessBoard = new ChessBoard(pieces);
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

	public Score calculateScore(Team team) {
		Map<Location, Piece> map = chessBoard.giveMyPiece(team);

		Map<Piece, Boolean> collect = map.keySet().stream()
			.collect(Collectors.toMap(
				map::get,
				location -> findSameColumnPawnCount(map, location) >= COLUMN_PAWN_COUNT)
			);

		return new Score(collect);
	}

	private long findSameColumnPawnCount(Map<Location, Piece> map, Location location) {
		return map.keySet().stream()
			.filter(targetLocation -> targetLocation.isSameCol(location))
			.filter(targetLocation -> map.get(targetLocation) instanceof Pawn)
			.count();
	}

	public Map<Location, Piece> getBoard() {
		return chessBoard.getBoard();
	}
}
