package chess.domain;

import java.util.Map;

import chess.domain.piece.Piece;

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

	public void movePiece(Position source, Position target) {
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

	public Map<Position, Piece> getBoard() {
		assertState(GameState.GAME_RUNNING, GameState.GAME_END);
		return board.getBoard();
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
