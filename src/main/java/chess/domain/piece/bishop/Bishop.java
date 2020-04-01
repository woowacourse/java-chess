package chess.domain.piece.bishop;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Bishop extends Piece {
	public static final String WHITE_BISHOP = "\u2657";
	public static final String BLACK_BISHOP = "\u265d";
	public static final double BISHOP_SCORE = 3;

	private final String symbol = "b";

	public String getSymbol() {
		return symbol;
	}

	public Bishop(Team team, Position position) {
		super(new BishopStrategy(), team, position);
	}

	public static Piece of(Team team, Position position) {
		return new Bishop(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_BISHOP;
		}
		return BLACK_BISHOP;
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return BISHOP_SCORE;
	}
}
