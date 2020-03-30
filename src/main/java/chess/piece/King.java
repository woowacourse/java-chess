package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.team.Team;

public class King extends Piece {
	private static final char name = 'k';
	private static final double score = 0;

	private King(Team team) {
		super(team);
	}

	public static King of(Team team) {
		King king = KingCache.kingCache.get(team);
		Objects.requireNonNull(king, "비숍이 존재하지 않습니다.");
		return king;
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isKingRange(after);
	}

	@Override
	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
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

	private static class KingCache {
		private static Map<Team, King> kingCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				kingCache.put(team, new King(team));
			}
		}
	}
}
