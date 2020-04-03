package chess.domain.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Ready implements GameState {
	private final Board board;
	private Team turn;

	public Ready(Board board) {
		this.board = board;
		this.turn = Team.WHITE;
	}

	public Ready(Board board, Team turn) {
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
		throw new UnsupportedOperationException("게임을 시작해야 합니다.");
	}

	@Override
	public Map<Team, Double> status() {
		throw new UnsupportedOperationException("게임을 시작해야 합니다.");
	}

	@Override
	public boolean isNotFinished() {
		return true;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public GameState end() {
		return new SuspendFinish(board, turn);
	}
}
