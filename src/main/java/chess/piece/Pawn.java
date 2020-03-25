package chess.piece;

import java.util.List;

import chess.position.Position;

public class Pawn extends Piece {
	public Pawn(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		return null;
	}
}
