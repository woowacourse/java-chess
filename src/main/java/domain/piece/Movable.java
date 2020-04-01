package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Position;
import domain.piece.team.Team;

public interface Movable {
	void canMove(Position targetPosition, Team turn, List<Rank> ranks);

	void move(Position targetPosition, List<Rank> board);
}
