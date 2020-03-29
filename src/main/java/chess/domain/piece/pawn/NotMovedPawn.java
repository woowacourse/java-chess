package chess.domain.piece.pawn;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class NotMovedPawn extends Pawn {
	private final MovingStrategy STRATEGY = new NotMovedPawnStrategy(team);

	private NotMovedPawn(Team team, Position position) {
		super(team, position);
	}

	public static NotMovedPawn of(Team team, Position position) {
		return new NotMovedPawn(team, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		STRATEGY.validateMove(from, to, dto);
		this.position = to;
		return MovedPawn.of(team, position);
	}
}
