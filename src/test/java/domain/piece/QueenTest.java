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

public class QueenTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();
	}

	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"e3, WHITE, f5", "e3, WHITE, d6", "g6, BLACK, f4", "e6, BLACK, c5"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Queen(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"c1, WHITE, e3", "f1, WHITE, d3", "c8, BLACK, e6", "f8, BLACK, d6"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Queen(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}
}
