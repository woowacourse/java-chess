package chess.domain.state;

import static chess.domain.piece.Team.*;
import static chess.domain.state.StateType.*;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.result.Result;

public class Ready extends GameState {
	public Ready(Board board) {
		this(board, NONE);
	}

	public Ready(Board board, Team turn) {
		super(board, READY, turn);
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
	public Result status() {
		throw new UnsupportedOperationException("게임을 시작해야 합니다.");
	}

	@Override
	public boolean isNotFinished() {
		return true;
	}

	@Override
	public GameState end() {
		return new SuspendFinished(board, turn);
	}
}
