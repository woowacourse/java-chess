package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class WhiteTurn implements State {

	private final Board board;

	public WhiteTurn(Board board) {
		this.board = board;
	}

	@Override
	public State start() {
		throw new IllegalStateException("이미 게임을 시작한 상태입니다.");
	}

	@Override
	public State end() {
		return new End();
	}

	@Override
	public State move(Coordinate from, Coordinate to) {
		Piece piece = board.findByCoordinate(from);

		if (piece.isBlack()) {
			throw new IllegalStateException("흰팀 차례에서는 흰색 말만 이동할 수 있습니다.");
		}

		Board newBoard = board.move(from, to);

		return new BlackTurn(newBoard);
	}

	@Override
	public Map<Coordinate, Piece> getValue() {
		return board.getValue();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
