package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.result.GameResult;
import chess.result.GameStatistic;
import chess.result.Score;
import chess.team.Team;

public class GameManager {
	private static final int COLUMN_PAWN_COUNT = 2;
	private final ChessBoard chessBoard;
	private GameState gameState;

	public GameManager(Map<Location, Piece> pieces) {
		this.chessBoard = new ChessBoard(pieces);
		this.gameState = GameState.RUNNING_BLACK_TURN;
	}

	public boolean isRunning() {
		return gameState.isGameRunning();
	}

	public void movePiece(Location now, Location destination) {
		checkTurn(now);

		if (chessBoard.canMove(now, destination)) {
			chessBoard.move(now, destination);
			gameState = gameState.of(chessBoard.hasTwoKings());
		}
	}

	private void checkTurn(Location now) {
		if (chessBoard.isTurn(now, gameState)) {
			throw new IllegalArgumentException("유저의 턴이 아닙니다.");
		}
	}

	public List<GameStatistic> createStatistics() {
		Score blackTeamScore = calculateScore(Team.WHITE);
		Score whiteTeamScore = calculateScore(Team.BLACK);

		List<GameStatistic> gameStatistics = new ArrayList<>();
		gameStatistics.add(
			new GameStatistic(Team.WHITE, whiteTeamScore, GameResult.findResult(whiteTeamScore, blackTeamScore)));
		gameStatistics.add(
			new GameStatistic(Team.BLACK, blackTeamScore, GameResult.findResult(blackTeamScore, whiteTeamScore)));
		return gameStatistics;
	}

	private Score calculateScore(Team team) {
		Map<Location, Piece> map = chessBoard.giveMyPiece(team);

		Map<Piece, Boolean> pieceAndVerticalPawnCheck = map.keySet().stream()
			.collect(Collectors.toMap(
				map::get,
				location -> findSameColumnPawnCount(map, location) >= COLUMN_PAWN_COUNT)
			);

		return new Score(team, pieceAndVerticalPawnCheck);
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
