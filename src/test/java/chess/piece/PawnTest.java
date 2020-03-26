package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static java.util.Collections.*;
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

public class PawnTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThat(pawn).isInstanceOf(Pawn.class);
	}

	@DisplayName("폰은 앞으로 1칸만큼 이동가능, 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void pawnPathTest(Position start, Position destination, List<Position> trace) {
		Pawn pawn = new Pawn(BLACK);
		List<Position> actual = pawn.findReachablePositions(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(E, FIVE), Position.of(E, SIX), Collections.emptyList()),
			Arguments.of(Position.of(E, FIVE), Position.of(E, SEVEN), singletonList(Position.of(E, SIX)))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Pawn king = new Pawn(BLACK);
		assertThatThrownBy(() -> king.findReachablePositions(Position.of(A, ONE), Position.of(B, THREE)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 위치로 이동할 수 없습니다.");
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,P", "WHITE,p"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Pawn(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}

	@DisplayName("처음 움직일 때 한 칸이나 두 칸을 움직여도 예외가 발생하지 않는지 테스트")
	@ParameterizedTest
	@CsvSource(value = {"a1,a2", "a1,a3"})
	void initialMoveTest(String start, String end) {
		Pawn pawn = new Pawn(BLACK);
		assertThatCode(() -> pawn.findReachablePositions(Position.of(start), Position.of(end)))
			.doesNotThrowAnyException();
	}

	@DisplayName("맨 처음 움직임 이후부터는 2칸을 움직였을때 예외가 발생하는지 테스트")
	@Test
	void afterInitialMoveTest() {

	}
}
