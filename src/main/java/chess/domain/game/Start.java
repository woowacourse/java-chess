package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class Start implements State {

	@Override
	public State start() {
		return new WhiteTurn(Board.create());
	}

	@Override
	public State end() {
		throw new IllegalStateException("");
	}

	@Override
	public State move(Coordinate from, Coordinate to) {
		throw new IllegalStateException("Start 상태에선 이동할 수 없습니다.");
	}

	@Override
	public Map<Coordinate, Piece> getValue() {
		return null;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
