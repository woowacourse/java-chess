package domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.BoardFactory;
import domain.board.Rank;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KingTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();

	}

	@DisplayName("킹 생성")
	@Test
	void constructor_CreateKing_Success() {
		Assertions.assertThat(new King(Position.of("b1"), Team.WHITE)).isInstanceOf(King.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"d1, WHITE, d1", "d8, BLACK, d8"})
	void canMove_SourceSameAsTarget_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new King(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"d1, WHITE, f2", "d1, WHITE, c3", "d8, BLACK, f7", "d8, BLACK, c6"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new King(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"d3, WHITE, b3", "e5, WHITE, e3", "h6, BLACK, f4"})
	void canMove_InvalidStepSize_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new King(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}
}
