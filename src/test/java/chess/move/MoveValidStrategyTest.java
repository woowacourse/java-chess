package chess.move;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.Board;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.Position;

class MoveValidStrategyTest {
	private Board board;

	@BeforeEach
	void setUp() {
		Map<Position, Piece> pieces = new HashMap<>();
		Piece pawn = new Pawn(Team.BLACK);
		pieces.put(Position.of(A, FOUR), pawn);
		board = new Board(pieces);
	}

	@DisplayName("MoveValidStrategy 객체 생성 테스트")
	@Test
	void constructTest() {
		MoveValidateStrategy moveValidateStrategy = new RookMoveValidateStrategy(new Board(null));
		assertThat(moveValidateStrategy).isInstanceOf(RookMoveValidateStrategy.class);
	}

	@DisplayName("주어진 말의 이동경로 중, 다른 말이 위치해 없어 이동이 가능한지 테스트")
	@ParameterizedTest
	@MethodSource("positionTraceSet")
	void isMovableTest(List<Position> trace, boolean expected) {
		MoveValidateStrategy moveValidateStrategy = new RookMoveValidateStrategy(board);
		boolean actual = moveValidateStrategy.isMovable(trace);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> positionTraceSet() {
		return Stream.of(
			Arguments.of(Arrays.asList(Position.of(A, ONE), Position.of(A, TWO), Position.of(A, THREE)), true),
			Arguments.of(Arrays.asList(Position.of(A, ONE), Position.of(A, TWO), Position.of(A, THREE),
				Position.of(A, FOUR), Position.of(A, FIVE), Position.of(A, SIX), Position.of(A, SEVEN)), false)
		);
	}
}