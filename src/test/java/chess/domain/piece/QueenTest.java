package chess.domain.piece;

import static chess.domain.piece.Team.*;
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

import chess.domain.position.Position;

public class QueenTest {
	@DisplayName("체스말 퀸은 직선, 대각선 거리로 이동할수 있으며 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void queenPathTest(Position start, Position destination, List<Position> trace) {
		Piece queen = new Queen(BLACK);
		List<Position> actual = queen.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of("c6"), Position.of("f3"),
				Arrays.asList(Position.of("d5"), Position.of("e4"))),
			Arguments.of(Position.of("f3"), Position.of("c6"),
				Arrays.asList(Position.of("e4"), Position.of("d5"))),
			Arguments.of(Position.of("c3"), Position.of("f6"),
				Arrays.asList(Position.of("d4"), Position.of("e5"))),
			Arguments.of(Position.of("f6"), Position.of("c3"),
				Arrays.asList(Position.of("e5"), Position.of("d4"))),

			Arguments.of(Position.of("b4"), Position.of("b7"),
				Arrays.asList(Position.of("b5"), Position.of("b6"))),
			Arguments.of(Position.of("b4"), Position.of("e4"),
				Arrays.asList(Position.of("c4"), Position.of("d4")))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Piece queen = new Queen(BLACK);
		assertThatThrownBy(() -> queen.findMoveModeTrace(Position.of("a1"), Position.of("c2")))
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
