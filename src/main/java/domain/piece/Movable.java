package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Position;
import domain.piece.team.Team;

public interface Movable {
	boolean canMove(Position targetPosition, Team turn, List<Rank> ranks);

	void move();
}
