package domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

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

public class KnightTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();
	}

	@DisplayName("나이트 생성")
	@Test
	void constructor_CreateKnight_Success() {
		assertThat(new Knight(Position.of("b1"), Team.WHITE)).isInstanceOf(Knight.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"c3, WHITE, b3", "c3, WHITE, d4", "c6, BLACK, b6", "c6, BLACK, d5"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Knight(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}
}
