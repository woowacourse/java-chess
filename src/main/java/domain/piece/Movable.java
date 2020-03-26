package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public interface Movable {
	boolean canMove(Position targetPosition, Team turn);

	void move();
}
