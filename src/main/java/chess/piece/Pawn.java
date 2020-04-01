package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.piece.stategy.PawnMoveStrategy;
import chess.team.Team;

public class Pawn extends Piece {
	private static final char name = 'p';
	private static final double score = 1;

	private Pawn(Team team) {
		super(team, new PawnMoveStrategy(team));
	}

	public static Pawn of(Team team) {
		Pawn pawn = PawnCache.pawnCache.get(team);
		Objects.requireNonNull(pawn, "비숍이 존재하지 않습니다.");
		return pawn;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public boolean isNotJumper() {
		return true;
	}

	@Override
	protected char getName() {
		return name;
	}

	private static class PawnCache {
		private static Map<Team, Pawn> pawnCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				pawnCache.put(team, new Pawn(team));
			}
		}
	}
}
