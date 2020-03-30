package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.team.Team;

public class Bishop extends Piece {
	private static final char name = 'b';
	private static final double score = 3;

	private Bishop(Team team) {
		super(team);
	}

	public static Bishop of(Team team) {
		Bishop bishop = BishopCache.bishopCache.get(team);
		Objects.requireNonNull(bishop, "비숍이 존재하지 않습니다.");
		return bishop;
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isDiagonal(after);
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	protected char getName() {
		return name;
	}

	private static class BishopCache {
		private static Map<Team, Bishop> bishopCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				bishopCache.put(team, new Bishop(team));
			}
		}
	}
}

