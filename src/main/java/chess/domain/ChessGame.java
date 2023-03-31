package chess.domain;

import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.result.ScoreAndWinnerResult;
import chess.repository.entity.MoveDto;

public class ChessGame {

	private static final String GAME_STATE_ERROR_MESSAGE = "현재 상태에서 불가능한 명령입니다.";

	private final Board board;
	private GameState state;
	private Team team;

	public ChessGame() {
		this.board = Board.create();
		this.state = GameState.UNINITIALIZED;
		this.team = Team.WHITE;
	}

	public void initialize() {
		assertState(GameState.UNINITIALIZED);
		changeState(GameState.GAME_RUNNING);
	}

	public void movePiece(final List<MoveDto> moves) {
		changeState(GameState.GAME_RUNNING);
		moves.forEach(move -> {
			Position source = new Position(move.getSourceColumn(), move.getSourceRow());
			Position target = new Position(move.getTargetColumn(), move.getTargetRow());
			movePiece(source, target);
		});
	}

	public void movePiece(final Position source, final Position target) {
		assertState(GameState.GAME_RUNNING);
		board.checkIsMovable(team, source, target);
		if (board.isKingPosition(target)) {
			changeState(GameState.GAME_END);
		}
		board.movePiece(team, source, target);
		alternateTeam();
	}

	private void alternateTeam() {
		team = team.alternate();
	}

	private void changeState(GameState state) {
		this.state = state;
	}

	public ScoreAndWinnerResult getScoreAndWinnerResult() {
		assertState(GameState.GAME_RUNNING);
		return ScoreAndWinnerResult.from(board.getBoard());
	}

	public Team getFinalWinner() {
		assertState(GameState.GAME_END, GameState.TERMINATED);
		return team;
	}

	public void terminate() {
		state = GameState.TERMINATED;
	}

	public boolean isUninitialized() {
		return state == GameState.UNINITIALIZED;
	}

	public boolean isGameRunning() {
		return state == GameState.GAME_RUNNING;
	}

	public boolean isGameDone() {
		return state == GameState.GAME_END;
	}

	public boolean isTerminated() {
		return state == GameState.TERMINATED;
	}

	public Map<Position, Piece> getBoard() {
		assertState(GameState.GAME_RUNNING, GameState.GAME_END);
		return board.getBoard();
	}

	public Team getTurn() {
		assertState(GameState.GAME_RUNNING, GameState.GAME_END);
		return team;
	}

	private void assertState(GameState validState1, GameState... validStates) {
		if (this.state == validState1) {
			return;
		}
		for (GameState validState : validStates) {
			if (this.state == validState) {
				return;
			}
		}
		throw new IllegalStateException(GAME_STATE_ERROR_MESSAGE);
	}
}
