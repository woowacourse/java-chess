package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class Start implements State {

    private final Board board;

    public Start(final Board board) {
        this.board = board;
    }

	@Override
	public State start() {
		return new WhiteTurn(board);
	}

	@Override
	public State end() {
		return new End(board);
	}

	@Override
	public State move(Coordinate from, Coordinate to) {
		throw new IllegalStateException("초기 상태에선 이동할 수 없습니다. 게임을 시작하거나 종료해 주세요.");
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
