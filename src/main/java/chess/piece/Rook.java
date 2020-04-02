package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.piece.stategy.RookMoveStrategy;
import chess.team.Team;

public class Rook extends Piece {
	private static final char name = 'r';
	private static final double score = 5;

	private Rook(Team team) {
		super(team, new RookMoveStrategy(team));
	}

	public static Rook of(Team team) {
		Rook rook = RookCache.rookCache.get(team);
		Objects.requireNonNull(rook, "룩이 존재하지 않습니다.");
		return rook;
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

	private static class RookCache {
		private static Map<Team, Rook> rookCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				rookCache.put(team, new Rook(team));
			}
		}
	}
}
