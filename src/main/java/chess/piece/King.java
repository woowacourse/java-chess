package chess.piece;

import java.util.List;

import chess.position.Position;

public class King extends Piece {
	public King(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		return null;
	}
}
