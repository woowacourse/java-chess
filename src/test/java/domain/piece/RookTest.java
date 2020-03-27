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

public class RookTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();
	}

	@DisplayName("룩 생성")
	@Test
	void constructor_CreateKnight_Success() {
		Assertions.assertThat(new Rook(Position.of("b1"), Team.WHITE)).isInstanceOf(Rook.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a3, WHITE, b4", "a3, WHITE, b5", "h6, BLACK, f4", "h6, BLACK, g4"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Rook(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a1, WHITE, a3", "a1, WHITE, c1", "h8, BLACK, h7", "h8, BLACK, g8"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Rook(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}
}
