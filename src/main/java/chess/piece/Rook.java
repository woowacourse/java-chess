package chess.piece;

import java.util.List;

import chess.position.Position;

public class Rook extends Piece {
	public Rook(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		if (start.isNotStraight(end)) {

		}

		return null;
	}
}
