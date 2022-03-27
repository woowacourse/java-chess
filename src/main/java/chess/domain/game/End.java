package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class End implements State {

	private final Board board;

	public End(Board board) {
		this.board = board;
	}

	@Override
	public State start() {
		return new End(board);
	}

	@Override
	public State end() {
		return new End(board);
	}

	@Override
	public State move(Coordinate from, Coordinate to) {
		return new End(board);
	}

	@Override
	public Map<Coordinate, Piece> getValue() {
		return board.getValue();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
