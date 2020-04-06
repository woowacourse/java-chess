package chess.domain.piece.pawn;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Pawn extends Piece {
	public static final String WHITE_PAWN = "\u2659";
	public static final String BLACK_PAWN = "\u265f";
	public static final int PAWN_SCORE = 1;

	private final String symbol = "p";

	public Pawn(Team team, Position position) {
		super(new PawnStrategy(team), team, position);
	}

	public static Pawn of(Team team, Position position) {
		return new Pawn(team, position);
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> teamBoard) {
		strategy.validateMove(from, to, teamBoard);
		this.position = to;
		return this;
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_PAWN;
		}
		return BLACK_PAWN;
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}

	public String getSymbol() {
		return symbol;
	}
}