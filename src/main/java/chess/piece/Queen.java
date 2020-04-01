package chess.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.piece.stategy.QueenMoveStrategy;
import chess.team.Team;

public class Queen extends Piece {
	private static final char name = 'q';
	private static final double score = 9;

	private Queen(Team team) {
		super(team, new QueenMoveStrategy(team));
	}

	public static Queen of(Team team) {
		Queen queen = QueenCache.queenCache.get(team);
		Objects.requireNonNull(queen, "비숍이 존재하지 않습니다.");
		return queen;
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

	private static class QueenCache {
		private static Map<Team, Queen> queenCache = new HashMap<>();

		static {
			for (Team team : Team.values()) {
				queenCache.put(team, new Queen(team));
			}
		}
	}
}
