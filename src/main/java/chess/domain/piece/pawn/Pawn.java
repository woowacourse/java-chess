package chess.domain.piece.pawn;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class Pawn extends Piece {
	public static final String WHITE_PAWN = "\u2659";
	public static final String BLACK_PAWN = "\u265f";
	public static final int PAWN_SCORE = 1;

	public Pawn(Team team, Position position) {
		super(new PawnStrategy(team), team, position);
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
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}
}