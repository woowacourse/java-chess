package chess.domain.piece;

import static chess.domain.piece.Team.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.position.Position3;

public class QueenTest {
	@DisplayName("체스말 퀸은 직선, 대각선 거리로 이동할수 있으며 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void queenPathTest(Position3 start, Position3 destination, List<Position3> trace) {
		Queen queen = new Queen(BLACK);
		List<Position3> actual = queen.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position3.of(C, SIX), Position3.of(F, THREE),
				Arrays.asList(Position3.of(D, FIVE), Position3.of(E, FOUR))),
			Arguments.of(Position3.of(F, THREE), Position3.of(C, SIX),
				Arrays.asList(Position3.of(E, FOUR), Position3.of(D, FIVE))),
			Arguments.of(Position3.of(C, THREE), Position3.of(F, SIX),
				Arrays.asList(Position3.of(D, FOUR), Position3.of(E, FIVE))),
			Arguments.of(Position3.of(F, SIX), Position3.of(C, THREE),
				Arrays.asList(Position3.of(E, FIVE), Position3.of(D, FOUR))),

			Arguments.of(Position3.of(B, FOUR), Position3.of(B, SIX),
				Arrays.asList(Position3.of(B, FIVE))),
			Arguments.of(Position3.of(B, FOUR), Position3.of(E, FOUR),
				Arrays.asList(Position3.of(C, FOUR), Position3.of(D, FOUR)))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Queen queen = new Queen(BLACK);
		assertThatThrownBy(() -> queen.findMoveModeTrace(Position3.of(A, ONE), Position3.of(C, TWO)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,Q", "WHITE,q"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Queen(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
