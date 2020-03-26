package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KnightTest {
	@DisplayName("나이트 생성")
	@Test
	void constructor_CreateKnight_Success() {
		assertThat(new Knight(Position.of("b1"), Team.WHITE)).isInstanceOf(Knight.class);
	}

	@DisplayName("유효한 목적지가 입력되면 true 반환")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, c3", "b1, WHITE, d2", "g1, WHITE, h3", "b8, BLACK, c6"})
	void canMove_ValidTargetPosition_ReturnTrue(Position sourcePosition, Team team, Position targetPosition) {
		assertThat(new Knight(sourcePosition, team).canMove(targetPosition, team)).isTrue();
	}

	@DisplayName("유효하지 않은 목적지가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, c4", "b1, WHITE, c1", "g1, WHITE, g3", "b8, BLACK, a7"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Knight(sourcePosition, team).canMove(targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_TARGET_POSITION);
	}
}
