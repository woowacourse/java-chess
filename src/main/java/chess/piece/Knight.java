package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.team.Team;

public class Knight extends Piece {
	private static final char name = 'n';
	private static final double score = 2.5;

	private Knight(Team team) {
		super(team);
	}

	public static Knight of(Team team) {
		Knight knight = KnightCache.knightCache.get(team);
		Objects.requireNonNull(knight, "비숍이 존재하지 않습니다.");
		return knight;
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return false;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	protected char getName() {
		return name;
	}

	@Override
	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}

	private static class KnightCache {
		private static Map<Team, Knight> knightCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				knightCache.put(team, new Knight(team));
			}
		}
	}
}
