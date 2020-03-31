package chess.domain.piece;

import static chess.domain.piece.Team.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.position.Position3;

public class PawnTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThat(pawn).isInstanceOf(Pawn.class);
	}

	@DisplayName("흑색폰은 앞으로 1칸만큼 아래로 이동가능, 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("blackStartDestinationTraceProvider")
	void blackPawnPathTest(Position3 start, Position3 destination, List<Position3> trace) {
		Pawn blackPawn = new Pawn(BLACK);
		List<Position3> actual = blackPawn.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> blackStartDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, FOUR), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, THREE), singletonList(Position3.of(E, FOUR)))
		);
	}

	@DisplayName("백색폰은 앞으로 1칸만큼 로 이동가능, 이동 경로를 반환할 수 있다.")
	@ParameterizedTest
	@MethodSource("whiteStartDestinationTraceProvider")
	void whitePawnPathTest(Position3 start, Position3 destination, List<Position3> trace) {
		Pawn whitePawn = new Pawn(WHITE);
		List<Position3> actual = whitePawn.findMoveModeTrace(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> whiteStartDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, SIX), Collections.emptyList()),
			Arguments.of(Position3.of(E, FIVE), Position3.of(E, SEVEN), singletonList(Position3.of(E, SIX)))
		);
	}

	@DisplayName("백색폰은 대각선 한칸 앞에 적 기물이 위치한 경우 기물 위치로 움직일 수 있다.")
	@ParameterizedTest
	@MethodSource("whiteStartDestinationProvider")
	void whitePawnCatchTest(Position3 start, Position3 destination) {
		HashMap<Position3, Piece> pieces = new HashMap<>();
		pieces.put(start, new Pawn(WHITE));
		pieces.put(destination, new Rook(BLACK));
		Board board = new Board(pieces);

		assertThatCode(() -> board.move(start, destination)).doesNotThrowAnyException();
	}

	private static Stream<Arguments> whiteStartDestinationProvider() {
		return Stream.of(
			Arguments.of(Position3.of(A, TWO), Position3.of(B, THREE)),
			Arguments.of(Position3.of(E, FIVE), Position3.of(F, SIX))
		);
	}

	@DisplayName("흑색폰은 대각선 한칸 아래 적 기물이 위치한 경우 기물 위치로 움직일 수 있다.")
	@ParameterizedTest
	@MethodSource("blackStartDestinationProvider")
	void blackPawnCatchTest(Position3 start, Position3 destination) {
		HashMap<Position3, Piece> pieces = new HashMap<>();
		pieces.put(start, new Pawn(BLACK));
		pieces.put(destination, new Rook(WHITE));
		pieces.put(Position3.of("d4"), new Rook(WHITE));
		Board board = new Board(pieces);
		board.move(Position3.of("d4"), Position3.of("d8"));

		assertThatCode(() -> board.move(start, destination)).doesNotThrowAnyException();
	}

	private static Stream<Arguments> blackStartDestinationProvider() {
		return Stream.of(
			Arguments.of(Position3.of(A, TWO), Position3.of(B, ONE)),
			Arguments.of(Position3.of(E, FIVE), Position3.of(F, FOUR))
		);
	}

	@DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
	@Test
	void invalidMovementTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThatThrownBy(() -> pawn.findMoveModeTrace(Position3.of(A, FOUR), Position3.of(B, THREE)))
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

	@DisplayName("맨 처음 움직임 이후부터는 2칸을 움직였을때 예외가 발생하는지 테스트")
	@Test
	void afterInitialMoveTest() {
		HashMap<Position3, Piece> pieces = new HashMap<>();
		pieces.put(Position3.of("a2"), new Pawn(WHITE));
		pieces.put(Position3.of("d4"), new Rook(BLACK));
		Board board = new Board(pieces);
		board.move(Position3.of("a2"), Position3.of("a3"));
		board.move(Position3.of("d4"), Position3.of("d7"));
		assertThatThrownBy(() -> board.move(Position3.of("a3"), Position3.of("a5")))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
