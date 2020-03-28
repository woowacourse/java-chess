package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
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

import chess.position.Position;

public class KingTest {
	@DisplayName("킹은 직선, 대각선 1칸만큼 이동가능, 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void kingPathTest(Position start, Position destination, List<Position> trace) {
		King king = new King(BLACK);
		List<Position> actual = king.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(E, FIVE), Position.of(D, SIX), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(E, SIX), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(F, SIX), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(D, FIVE), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(F, FIVE), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(D, FOUR), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(E, FOUR), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(F, FOUR), Collections.emptyList())
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		King king = new King(BLACK);
		assertThatThrownBy(() -> king.findMoveModeTrace(Position.of(A, ONE), Position.of(B, THREE)))
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
