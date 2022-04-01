package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

	@ParameterizedTest
	@CsvSource(value = {"THREE, D, BLACK", "FIVE, D, WHITE"})
	void validateMovement(Rank rank, File file, Team team) {
		Position source = Position.of(FOUR, D);
		Piece sourcePawn = new Pawn(team);
		Position target = Position.of(rank, file);
		Piece blank = new Blank();

		assertDoesNotThrow(
				() -> sourcePawn.validateMovement(source, target, blank));
	}

	@ParameterizedTest
	@CsvSource(value = {"THREE, C, BLACK, WHITE", "THREE, E, BLACK, WHITE", "FIVE, C, WHITE, BLACK",
			"FIVE, E, WHITE, BLACK"})
	void validateAttackMovement(Rank rank, File file, Team ally, Team enemy) {
		Position source = Position.of(FOUR, D);
		Piece sourcePawn = new Pawn(ally);
		Position target = Position.of(rank, file);
		Piece targetPawn = new Pawn(enemy);

		assertDoesNotThrow(
				() -> sourcePawn.validateMovement(source, target, targetPawn));
	}

	@ParameterizedTest
	@CsvSource(value = {"TWO, FOUR,  WHITE", "SEVEN, FIVE, BLACK"})
	void validateInitialPositionMovement(Rank sourceRank, Rank targetRank, Team team) {
		Piece sourcePawn = new Pawn(team);
		Position source = Position.of(sourceRank, A);
		Position movedTwoStep = Position.of(targetRank, A);
		Piece blank = new Blank();

		assertDoesNotThrow(() -> sourcePawn.validateMovement(source, movedTwoStep, blank));
	}

	@ParameterizedTest
	@CsvSource(value = {"THREE, FIVE, WHITE", "SIX, FOUR, BLACK"})
	void validateNotInitialPositionMovementException(Rank sourceRank, Rank targetRank, Team team) {
		Piece sourcePawn = new Pawn(team);
		Position source = Position.of(sourceRank, A);
		Position movedTwoStep = Position.of(targetRank, A);
		Piece blank = new Blank();

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, movedTwoStep, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"THREE, C, WHITE", "THREE, D, WHITE", "THREE, E, WHITE", "FIVE, C, BLACK", "FIVE, D, BLACK",
			"FIVE, E, BLACK"})
	void validateNotAttackDirectionException(Rank rank, File file, Team ally) {
		Position source = Position.of(FOUR, D);
		Piece sourcePawn = new Pawn(ally);
		Position target = Position.of(rank, file);
		Piece blank = new Blank();

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(FOUR, D);
		Piece sourcePawn = new Pawn(Team.BLACK);
		Position target = Position.of(THREE, C);
		Piece targetPawn = new Pawn(Team.BLACK);

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, target, targetPawn))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
