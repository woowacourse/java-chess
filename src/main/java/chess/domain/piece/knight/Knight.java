package chess.domain.piece.knight;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Knight extends Piece {
	private static final String WHITE_KNIGHT = "\u2658";
	private static final String BLACK_KNIGHT = "\u265e";
	public static final double KNIGHT_SCORE = 2.5;

	public Knight(Team team, Position position) {
		super(new KnightStrategy(), team, position);
	}

	public static Knight of(Team team, Position position) {
		return new Knight(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_KNIGHT;
		}
		return BLACK_KNIGHT;
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return KNIGHT_SCORE;
	}
}