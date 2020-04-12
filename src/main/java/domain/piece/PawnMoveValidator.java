package domain.piece;

import java.util.Arrays;
import java.util.function.Predicate;

import domain.piece.position.Direction;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public enum PawnMoveValidator {
	WHITE_PAWN(Team.WHITE, (direction) -> Direction.whitePawnDirection().contains(direction), 2),
	BLACK_PAWN(Team.BLACK, (direction) -> Direction.blackPawnDirection().contains(direction), 7);

	private Team team;
	private Predicate<Direction> pawnDirectionValidation;
	private int initialPawnRank;

	PawnMoveValidator(Team team, Predicate<Direction> pawnDirectionValidation, int initialPawnRank) {
		this.team = team;
		this.pawnDirectionValidation = pawnDirectionValidation;
		this.initialPawnRank = initialPawnRank;
	}

	public static boolean isValidPawnDirection(Direction direction, Team team) {
		return Arrays.stream(PawnMoveValidator.values())
			.anyMatch(pawn -> pawn.pawnDirectionValidation.test(direction) && pawn.team.equals(team));
	}

	public static boolean isInitialRank(int rank, Team team) {
		return Arrays.stream(PawnMoveValidator.values())
			.anyMatch(pawn -> (pawn.initialPawnRank == rank) && (pawn.team.equals(team)));
	}
}
