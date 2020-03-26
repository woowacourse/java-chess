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

public class QueenTest {
	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}

	@DisplayName("유효한 목적지가 입력되면 true 반환")
	@ParameterizedTest
	@CsvSource({"e1, WHITE, e8", "e1, WHITE, a1", "e1, WHITE, h4", "e8, BLACK, b5", "f3, BLACK, b7"})
	void canMove_ValidTargetPosition_ReturnTrue(Position sourcePosition, Team team, Position targetPosition) {
		assertThat(new Queen(sourcePosition, team).canMove(targetPosition, team)).isTrue();
	}

	@DisplayName("유효하지 않은 목적지가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"e1, WHITE, g2", "e1, WHITE, a4", "e1, WHITE, b2", "e8, BLACK, a3", "f3, BLACK, e5", "f4, BLACK, f4"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Queen(sourcePosition, team).canMove(targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_TARGET_POSITION);
	}
}
