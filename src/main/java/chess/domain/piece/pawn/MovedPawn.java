package chess.domain.piece.pawn;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class MovedPawn extends Pawn {
	private final MovingStrategy STRATEGY = new PawnStrategy(team);

	private MovedPawn(Team team, Position position) {
		super(team, position);
	}

	public static MovedPawn of(Team team, Position position) {
		return new MovedPawn(team, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		STRATEGY.validateMove(from, to, dto);
		this.position = to;
		return this;
	}
}
