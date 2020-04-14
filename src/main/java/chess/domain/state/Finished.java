package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

public abstract class Finished extends GameState {
	public Finished(Board board, StateType stateType, Team turn) {
		super(board, stateType, turn);
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
	public Result status() {
		return Result.from(board);
	}

	@Override
	public GameState end() {
		throw new UnsupportedOperationException("이미 게임이 끝났습니다.");
	}

	@Override
	public boolean isNotFinished() {
		return false;
	}
}
