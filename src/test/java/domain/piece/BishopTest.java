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

public class BishopTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();
	}

	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop(Position.of("b1"), Team.WHITE)).isInstanceOf(Bishop.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"c3, WHITE, b3", "c3, WHITE, d3","c3, WHITE, e4", "c6, BLACK, b6", "c6, BLACK, d6"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Bishop(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"c1, WHITE, e3", "f1, WHITE, d3", "c8, BLACK, e6", "f8, BLACK, d6"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Bishop(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}
}
