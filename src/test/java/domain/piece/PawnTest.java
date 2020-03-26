package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class PawnTest {
	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn(Position.of("b1"), Team.WHITE)).isInstanceOf(Pawn.class);
	}

	@DisplayName("유효한 목적지가 입력되면 true 반환")
	@ParameterizedTest
	@CsvSource({"d2, WHITE, d3", "h4, WHITE, h5", "d7, BLACK, d6", "f3, BLACK, f2", "d3, WHITE, e4", "e6, BLACK, f5"})
	void canMove_ValidTargetPosition_ReturnTrue(Position sourcePosition, Team team, Position targetPosition) {
		assertThat(new Pawn(sourcePosition, team).canMove(targetPosition, team)).isTrue();
	}

	@DisplayName("유효하지 않은 목적지가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"d1, WHITE, b1", "f2, WHITE, g4", "h3, WHITE, g5", "d8, BLACK, c6"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Pawn(sourcePosition, team).canMove(targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_TARGET_POSITION);
	}
}
