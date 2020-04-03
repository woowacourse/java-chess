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

public class RookTest {
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void rookPathTest(Position start, Position destination, List<Position> trace) {
		Piece rook = new Rook(BLACK);
		List<Position> actual = rook.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of("b4"), Position.of("b7"),
				Arrays.asList(Position.of("b5"), Position.of("b6"))),
			Arguments.of(Position.of("b4"), Position.of("e4"),
				Arrays.asList(Position.of("c4"), Position.of("d4")))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Piece rook = new Rook(BLACK);
		assertThatThrownBy(() -> rook.findMoveModeTrace(Position.of("a1"), Position.of("b2")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,R", "WHITE,r"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Rook(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
