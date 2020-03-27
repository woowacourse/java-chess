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
import domain.board.BoardFixture;
import domain.board.Rank;
import domain.piece.pawn.Pawn;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class PawnTest {
	private static List<Rank> ranks;

	@BeforeEach
	void setUp() {
		ranks = BoardFactory.create().getRanks();
	}

	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn(Position.of("b1"), Team.WHITE)).isInstanceOf(Pawn.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b3, WHITE, c3", "b3, WHITE, a3", "b2, WHITE, c4", "e5, BLACK, d3", "h6, BLACK, g6"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Pawn(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	// 우선 말이 움직일 수 있는 칸이 1,2칸 모두라고 가정한다.
	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a2, WHITE, a5", "f7, BLACK, f4"})
	void canMove_InvalidStepSize_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> new Pawn(sourcePosition, team).canMove(targetPosition, team, ranks))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a2, WHITE, a4"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		List<Rank> customBoard = BoardFixture.create().getRanks();
		assertThatThrownBy(() -> new Pawn(sourcePosition, team).canMove(targetPosition, team, customBoard))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}
}