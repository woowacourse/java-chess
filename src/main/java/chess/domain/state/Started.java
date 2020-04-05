package chess.domain.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Started implements GameState {
	private final Board board;
	private Team turn;

	public Started(Board board) {
		this.board = board;
		this.turn = Team.WHITE;
	}

	public Started(Board board, Team turn) {
		this.board = board;
		this.turn = turn;
	}

	@Override
	public GameState start() {
		board.start();
		return new Started(board);
	}

	@Override
	public GameState move(Position from, Position to) {
		validateRightTurn(from);
		board.move(from, to);
		if (board.containsSingleKingWith(turn)) {
			return new KingCatchFinished(board, turn);
		}
		switchTurn();
		return this;
	}

	private void validateRightTurn(Position from) {
		if (board.isNotSameTeamFromPosition(from, turn)) {
			throw new IllegalArgumentException("움직일 수 없는 턴입니다.");
		}
	}

	private void switchTurn() {
		this.turn = turn.getOppositeTeam();
	}

	@Override
	public Map<Team, Double> status() {
		return board.status();
	}

	@Override
	public GameState end() {
		return new SuspendFinished(board, turn);
	}

	@Override
	public boolean isNotFinished() {
		return true;
	}

	@Override
	public Board getBoard() {
		return board;
	}
}
