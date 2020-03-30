package chess.piece;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class Pawn extends Piece {
	private static final char name = 'p';
	private static final double score = 1;

	public Pawn(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		Team team = this.team;

		if (now.isInitialPawnLocation(team)) {
			return now.isInitialPawnForwardRange(after, team)
				|| now.isForwardDiagonal(after, team);
		}
		return now.isPawnForwardRange(after, team) ||
			now.isForwardDiagonal(after, team);
	}

	@Override
	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return !isMovable(board, now, destination);
	}

	private boolean isMovable(Map<Location, Piece> board, Location now, Location destination) {
		// 대각선 방향 && 목적지에 피스가 있는경우
		if (now.isDiagonal(destination) && board.containsKey(destination)) {
			Piece maybeEnemyPiece = board.get(destination);
			return maybeEnemyPiece.isSameTeam(team.ofOpposingTeam());
		}

		Location nextLocation = now.calculateNextLocation(destination, 1);
		return (!board.containsKey(destination) && !board.containsKey(nextLocation))
			&& now.isSameCol(destination);
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	protected char getName() {
		return name;
	}
}
