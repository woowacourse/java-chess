package chess.domain.piece.pawn;

import java.util.List;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.Team;
import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class PawnMoveStrategy implements MoveStrategy {
	private final Team team;

	public PawnMoveStrategy(Team team) {
		this.team = team;
	}

	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		List<Position> movable = MovableAreaFactory.pawnOf(start, team);
		if (team.isInitialPawnRow(start.getRow())) {
			movable.add(Position.of(start.getColumn(), team.getSpecialMoveOfPawn()));
		}
		return !movable.contains(destination);
	}
}
