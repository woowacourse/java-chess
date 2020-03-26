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

public class BishopTest {
	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop(Position.of("b1"), Team.WHITE)).isInstanceOf(Bishop.class);
	}

	@DisplayName("유효한 목적지가 입력되면 true 반환")
	@ParameterizedTest
	@CsvSource({"c1, WHITE, g5", "f1, WHITE, a6", "d4, BLACK, g1", "g8, BLACK, h7"})
	void canMove_ValidTargetPosition_ReturnTrue(Position sourcePosition, Team team, Position targetPosition) {
		assertThat(new Bishop(sourcePosition, team).canMove(targetPosition, team)).isTrue();
	}

	@DisplayName("유효하지 않은 목적지가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"c1, WHITE, g4", "f1, WHITE, a5", "d4, BLACK, g3", "g8, BLACK, g8"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Bishop(sourcePosition, team).canMove(targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_TARGET_POSITION);
	}
}
