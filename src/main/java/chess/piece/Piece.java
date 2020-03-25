package chess.piece;

import java.util.List;

import chess.position.Position;

public abstract class Piece {
	private final Team team;


	public Piece(Team team) {
		this.team = team;
	}

    //public abstract List<Position> findReachablePositions(Position start, Position end);
}
