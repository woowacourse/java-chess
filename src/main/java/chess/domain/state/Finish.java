package chess.domain.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public abstract class Finish implements GameState {
	protected final Board board;
	protected Team turn;

	public Finish(Board board, Team turn) {
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
		throw new UnsupportedOperationException("이미 게임이 끝났습니다.");
	}

	@Override
	public Map<Team, Double> status() {
		return board.status();
	}

	@Override
	public GameState end() {
		throw new UnsupportedOperationException("이미 게임이 끝났습니다.");
	}

	@Override
	public boolean isNotFinished() {
		return false;
	}

	@Override
	public Board getBoard() {
		return board;
	}
}
