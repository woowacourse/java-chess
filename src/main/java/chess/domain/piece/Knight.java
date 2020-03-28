package chess.domain.piece;

import java.util.Map;

import chess.domain.Team;
import chess.domain.position.Position;

public class Knight extends Piece {
	public static final String WHITE_KNIGHT = "\u2658";
	public static final String BLACK_KNIGHT = "\u265e";

	public Knight(Team team, Position position) {
		super(team, position);
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
		return null;
	}
}