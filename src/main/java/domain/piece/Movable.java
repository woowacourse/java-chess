package domain.piece;

import domain.board.Board;
import domain.piece.position.Position;
import domain.piece.team.Team;

public interface Movable {
	void canMove(Position targetPosition, Team turn, Board ranks);

	void move(Position targetPosition, Board ranks);
}
