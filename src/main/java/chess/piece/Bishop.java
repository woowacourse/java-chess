package chess.piece;

import java.util.List;

import chess.position.Position;

public class Bishop extends Piece {
	public Bishop(Team team) {
		super(team, "B");
	}

	@Override
	public List<Position> getMovablePositionsRegardlessOtherPieces(Position position) {
		return null;
	}

	@Override
	public List<Position> findTraceBetween(Position start, Position end) {
		if (start.isNotDiagonal(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Position.findDiagonalTrace(start, end);
	}
}
