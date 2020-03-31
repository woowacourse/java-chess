package chess.domain.piece;

import static chess.domain.piece.Team.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.position.Position3;

public class KingTest {
	@DisplayName("킹은 직선, 대각선 1칸만큼 이동가능, 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void kingPathTest(Position3 start, Position3 destination, List<Position3> trace) {
		King king = new King(BLACK);
		List<Position3> actual = king.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position3.of(E, FIVE), Position3.of(D, SIX), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, SIX), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(F, SIX), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(D, FIVE), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(F, FIVE), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(D, FOUR), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, FOUR), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(F, FOUR), Collections.emptyList())
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		King king = new King(BLACK);
		assertThatThrownBy(() -> king.findMoveModeTrace(Position3.of(A, ONE), Position3.of(B, THREE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,K", "WHITE,k"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new King(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
