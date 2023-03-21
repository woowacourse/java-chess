package chess.service;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class ChessService {

	private static final String SERVICE_STATE_ERROR_MESSAGE = "현재 상태에서 불가능한 명령입니다.";

	private final Board board;
	private ChessState state;
	private Turn turn;

	public ChessService() {
		this.board = Board.create();
		this.state = ChessState.UNINITIALIZED;
		this.turn = Turn.WHITE;
	}

	public void initialize() {
		assertState(ChessState.UNINITIALIZED);
		changeState(ChessState.GAME_RUNNING);
	}

	public void movePiece(Position source, Position target) {
		assertState(ChessState.GAME_RUNNING);
		Team team = getCurrentTeam();
		board.checkIsMovable(team, source, target);
		board.movePiece(team, source, target);
		alternateTurn();
	}

	private Team getCurrentTeam() {
		if (turn == Turn.WHITE) {
			return Team.WHITE;
		}
		return Team.BLACK;
	}

	private void alternateTurn() {
		if (turn == Turn.WHITE) {
			turn = Turn.BLACK;
			return;
		}
		if (turn == Turn.BLACK) {
			turn = Turn.WHITE;
		}
	}

	private void changeState(ChessState state) {
		this.state = state;
	}

	public Map<Position, Piece> getBoard() {
		assertState(ChessState.GAME_RUNNING);
		return board.getBoard();
	}

	private void assertState(ChessState validState1, ChessState... validStates) {
		if (this.state == validState1) {
			return;
		}
		for (ChessState validState : validStates) {
			if (this.state == validState) {
				return;
			}
		}
		throw new IllegalStateException(SERVICE_STATE_ERROR_MESSAGE);
	}
}
