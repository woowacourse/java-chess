package chess.domain.piece.pawn;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class Pawn extends Piece {
	private final MovingStrategy STRATEGY = new PawnStrategy(team);
	public static final String WHITE_PAWN = "\u2659";
	public static final String BLACK_PAWN = "\u265f";

	public Pawn(Team team, Position position) {
		super(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_PAWN;
		}
		return BLACK_PAWN;
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		STRATEGY.validateMove(from, to, dto);
		this.position = to;
		return this;
	}
}