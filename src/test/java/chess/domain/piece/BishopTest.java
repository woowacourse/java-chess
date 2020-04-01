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

public class BishopTest {
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void bishopPathTest(Position start, Position destination, List<Position> trace) {
		Bishop bishop = new Bishop(BLACK);
		List<Position> actual = bishop.findMoveModeTrace(start, destination);
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
				Arrays.asList(Position.of("e5"), Position.of("d4")))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Bishop bishop = new Bishop(BLACK);
		assertThatThrownBy(() -> bishop.findMoveModeTrace(Position.of("a1"), Position.of("b1")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,B", "WHITE,b"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Bishop(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
