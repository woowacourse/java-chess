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

import chess.domain.position.Position;

public class KnightTest {
	@DisplayName("나이트는 가로칸*세로칸 = 2를 성립하는 칸만큼 이동할수 있으며 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void knightPathTest(Position start, Position destination, List<Position> trace) {
		Knight knight = new Knight(BLACK);
		List<Position> actual = knight.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(E, FIVE), Position.of(D, SEVEN), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(F, SEVEN), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(G, SIX), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(G, FOUR), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(F, THREE), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(D, THREE), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(C, FOUR), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(C, SIX), Collections.emptyList())
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Knight knight = new Knight(BLACK);
		assertThatThrownBy(() -> knight.findMoveModeTrace(Position.of(A, ONE), Position.of(B, ONE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,N", "WHITE,n"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Knight(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
