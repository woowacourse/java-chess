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
import org.junit.jupiter.params.provider.MethodSource;

import chess.position.Position;

public class PieceTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThat(pawn).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@MethodSource("startDestinationTraceProvider")
	void rookPathTest(Position start, Position destination, List<Position> trace) {
		Rook rook = new Rook(BLACK);
		List<Position> actual = rook.findReachablePositions(start, destination);
		assertThat(actual).isEqualTo(trace);
	}

	private static Stream<Arguments> startDestinationTraceProvider() {
		return Stream.of(
			Arguments.of(Position.of(B, FOUR), Position.of(B, SIX), Arrays.asList(Position.of(B, FIVE), Position.of(B, SIX))),
			Arguments.of(Position.of(B, FOUR), Position.of(E, FOUR), Arrays.asList(Position.of(C, FOUR), Position.of(D, FOUR),
				Position.of(E, FOUR)))
		);
	}
}
