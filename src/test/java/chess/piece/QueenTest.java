package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
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

import chess.position.Position;

public class QueenTest {
    @DisplayName("체스말 퀸은 직선, 대각선 거리로 이동할수 있으며 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void rookPathTest(Position start, Position destination, List<Position> trace) {
		Queen queen = new Queen(BLACK);
		List<Position> actual = queen.findReachablePositions(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(C, SIX), Position.of(F, THREE),
				Arrays.asList(Position.of(D, FIVE), Position.of(E, FOUR), Position.of(F, THREE))),
			Arguments.of(Position.of(F, THREE), Position.of(C, SIX),
				Arrays.asList(Position.of(E, FOUR), Position.of(D, FIVE), Position.of(C, SIX))),
			Arguments.of(Position.of(C, THREE), Position.of(F, SIX),
				Arrays.asList(Position.of(D, FOUR), Position.of(E, FIVE), Position.of(F, SIX))),
			Arguments.of(Position.of(F, SIX), Position.of(C, THREE),
				Arrays.asList(Position.of(E, FIVE), Position.of(D, FOUR), Position.of(C, THREE))),

			Arguments.of(Position.of(B, FOUR), Position.of(B, SIX),
				Arrays.asList(Position.of(B, FIVE), Position.of(B, SIX))),
			Arguments.of(Position.of(B, FOUR), Position.of(E, FOUR),
				Arrays.asList(Position.of(C, FOUR), Position.of(D, FOUR), Position.of(E, FOUR)))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Queen queen = new Queen(BLACK);
		assertThatThrownBy(() -> queen.findReachablePositions(Position.of(A, ONE), Position.of(C, TWO)))
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
