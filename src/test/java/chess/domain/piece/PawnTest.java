package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece pawn = new Pawn(Team.BLACK);
		assertThat(pawn.getSymbol()).isEqualTo("P");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece pawn = new Pawn(Team.WHITE);
		assertThat(pawn.getSymbol()).isEqualTo("p");
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 4, BLACK", "5, 4, WHITE"})
	void validateMovement(int targetRow, int targetColumn, Team team) {
		Position source = Position.of(4, 4);
		Piece sourcePawn = new Pawn(team);
		Position target = Position.of(targetRow, targetColumn);
		Piece blank = new Blank();

		assertDoesNotThrow(
				() -> sourcePawn.validateMovement(source, target, blank));
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 3, BLACK, WHITE", "3, 5, BLACK, WHITE", "5, 3, WHITE, BLACK", "5, 5, WHITE, BLACK"})
	void validateAttackMovement(int targetRow, int targetColumn, Team ally, Team enemy) {
		Position source = Position.of(4, 4);
		Piece sourcePawn = new Pawn(ally);
		Position target = Position.of(targetRow, targetColumn);
		Piece targetPawn = new Pawn(enemy);

		assertDoesNotThrow(
				() -> sourcePawn.validateMovement(source, target, targetPawn));
	}

	@ParameterizedTest
	@CsvSource(value = {"2, 4,  WHITE", "7, 5, BLACK"})
	void validateInitialPositionMovement(int sourceRow, int targetRow, Team team) {
		Piece sourcePawn = new Pawn(team);
		Position source = Position.of(sourceRow, 1);
		Position movedTwoStep = Position.of(targetRow, 1);
		Piece blank = new Blank();

		assertDoesNotThrow(() -> sourcePawn.validateMovement(source, movedTwoStep, blank));
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 5,  WHITE", "6, 4, BLACK"})
	void validateNotInitialPositionMovementException(int sourceRow, int targetRow, Team team) {
		Piece sourcePawn = new Pawn(team);
		Position source = Position.of(sourceRow, 1);
		Position movedTwoStep = Position.of(targetRow, 1);
		Piece blank = new Blank();

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, movedTwoStep, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 3, WHITE", "3, 4, WHITE", "3, 5, WHITE", "5, 3, BLACK", "5, 4, BLACK", "5, 5, BLACK"})
	void validateNotAttackDirectionException(int targetRow, int targetColumn, Team ally) {
		Position source = Position.of(4, 4);
		Piece sourcePawn = new Pawn(ally);
		Position target = Position.of(targetRow, targetColumn);
		Piece blank = new Blank();

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourcePawn = new Pawn(Team.BLACK);
		Position target = Position.of(3, 3);
		Piece targetPawn = new Pawn(Team.BLACK);

		assertThatThrownBy(() -> sourcePawn.validateMovement(source, target, targetPawn))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
