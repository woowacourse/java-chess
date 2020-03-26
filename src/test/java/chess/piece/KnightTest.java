package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.position.Position;

public class KnightTest {
	@DisplayName("나이트는 가로칸*세로칸 = 2를 성립하는 칸만큼 이동할수 있으며 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void knightPathTest(Position start, Position destination, List<Position> trace) {
		Knight knight = new Knight(BLACK);
		List<Position> actual = knight.findReachablePositions(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(E, FIVE), Position.of(D, SEVEN), singletonList(Position.of(D, SEVEN))),
			Arguments.of(Position.of(E, FIVE), Position.of(F, SEVEN), singletonList(Position.of(F, SEVEN))),
			Arguments.of(Position.of(E, FIVE), Position.of(G, SIX), singletonList(Position.of(G, SIX))),
			Arguments.of(Position.of(E, FIVE), Position.of(G, FOUR), singletonList(Position.of(G, FOUR))),
			Arguments.of(Position.of(E, FIVE), Position.of(F, THREE), singletonList(Position.of(F, THREE))),
			Arguments.of(Position.of(E, FIVE), Position.of(D, THREE), singletonList(Position.of(D, THREE))),
			Arguments.of(Position.of(E, FIVE), Position.of(C, FOUR), singletonList(Position.of(C, FOUR))),
			Arguments.of(Position.of(E, FIVE), Position.of(C, SIX), singletonList(Position.of(C, SIX)))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Knight knight = new Knight(BLACK);
		assertThatThrownBy(() -> knight.findReachablePositions(Position.of(A, ONE), Position.of(B, ONE)))
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
