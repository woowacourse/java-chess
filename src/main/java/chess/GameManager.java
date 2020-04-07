package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.Piece;
import chess.result.GameResult;
import chess.result.GameStatistic;
import chess.result.Score;
import chess.team.Team;

public class GameManager {
	private final ChessBoard chessBoard;
	private GameState gameState;

	public GameManager(Map<Location, Piece> pieces, GameState gameState) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.chessBoard = new ChessBoard(pieces);
		this.gameState = gameState;
		// todo : 추가된 방어로직
		if (!chessBoard.hasTwoKings()) {
			this.gameState = GameState.END;
		}
	}

	public boolean isRunning() {
		return gameState.isGameRunning();
	}

	public void movePiece(Location now, Location destination) {
		if (!isRunning()) {
			throw new IllegalArgumentException("게임이 종료되었습니다.");
		}
		checkStarting(now);
		checkTurn(now);
		chessBoard.move(now, destination);
		gameState = gameState.of(chessBoard.hasTwoKings());
	}

	private void checkStarting(Location now) {
		if (chessBoard.isNotPiece(now)) {
			throw new IllegalArgumentException("출발지가 빈칸입니다.");
		}
	}

	private void checkTurn(Location now) {
		if (chessBoard.isNotSameTeam(gameState, now)) {
			throw new IllegalArgumentException("해당 유저의 턴이 아닙니다.");
		}
	}

	public List<GameStatistic> createStatistics() {
		Score blackTeamScore = chessBoard.calculateScore(Team.WHITE);
		Score whiteTeamScore = chessBoard.calculateScore(Team.BLACK);

		List<GameStatistic> gameStatistics = new ArrayList<>();
		gameStatistics.add(
			new GameStatistic(Team.WHITE, whiteTeamScore, GameResult.findResult(whiteTeamScore, blackTeamScore)));
		gameStatistics.add(
			new GameStatistic(Team.BLACK, blackTeamScore, GameResult.findResult(blackTeamScore, whiteTeamScore)));
		return gameStatistics;
	}

	public Team findWinner() {
		return chessBoard.findWinner();
	}

	public Map<Location, Piece> getBoard() {
		return chessBoard.getBoard();
	}

	public GameState getGameState() {
		return gameState;
	}
}
