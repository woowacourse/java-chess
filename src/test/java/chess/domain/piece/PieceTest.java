package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
	@Test
	@DisplayName("움직임 테스트")
	void move() {
		Piece piece = new King(Side.WHITE, Position.of("c5"));
		piece.move(Position.of("c6"));
		assertThat(piece.isSamePosition(Position.of("c6"))).isTrue();
	}

	@Test
	@DisplayName("targetPosition이 규칙에 맞게 이동 가능한 지 검사하는 기능 테스트")
	void canMove() {
		Piece piece = new King(Side.WHITE, Position.of("c5"));
		assertThat(piece.canMove(Position.of("c6")));
	}

	@Test
	@DisplayName("targetPiece가 공격 가능 한 지 검사하는 기능 테스트")
	void canAttack() {
		Piece piece1 = new Queen(Side.WHITE, Position.of("c5"));
		Piece piece2 = new Queen(Side.BLACK, Position.of("c6"));
		assertThat(piece1.canAttack(piece2)).isTrue();
	}

	@Test
	@DisplayName("targetPiece가 공격 가능 한 지 검사하는 기능 테스트 - 같은 편일시")
	void canAttackSameSide() {
		Piece piece1 = new Queen(Side.WHITE, Position.of("c5"));
		Piece piece2 = new King(Side.WHITE, Position.of("c6"));
		assertThat(piece1.canAttack(piece2)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,c2,false", "c3,a1,e5,true", "c5,c4,c2,false", "a3,a1,a5,true", "d6,a8,h1,false"})
	@DisplayName("두 포지션 사이를 기물이 가로막고 있는 지 검사하는 기능 테스트")
	void isBlock(String test, String source, String target, boolean expected) {
		Piece piece = new King(Side.WHITE, Position.of(test));
		assertThat(piece.isBlock(Position.of(source), Position.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,c4,true", "c3,a1,e5,true", "c5,c4,c2,false", "a3,a1,a5,true", "d6,a8,h1,true"})
	@DisplayName("두 포지션 사이 공간에 기물이 위치하는 지 검사하는 기능 테스트")
	void isBetween(String test, String source, String target, boolean expected) {
		Piece piece = new King(Side.WHITE, Position.of(test));
		assertThat(piece.isBetween(Position.of(source), Position.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource(value = "provideDirection")
	@DisplayName("올바른 방향을 찾는지 테스트")
	void findDirection(Direction expected, Position position1, Position position2) {
		Piece piece = new King(Side.WHITE, position1);
		assertThat(piece.findDirection(position2)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDirection() {
		return Stream.of(
				Arguments.of(Direction.COL, Position.of("a8"), Position.of("a7")),
				Arguments.of(Direction.ROW, Position.of("a8"), Position.of("b8")),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, Position.of("a5"), Position.of("e1")),
				Arguments.of(Direction.POSITIVE_DIAGONAL, Position.of("c5"), Position.of("e7")),
				Arguments.of(Direction.NON_LINEAR, Position.of("a8"), Position.of("d7"))
		);
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,false", "c3,a1,false", "c5,c4,false", "a3,a8,true", "d6,a1,false"})
	@DisplayName("진영별 공격방향인지 확인하는 기능 테스트 - 백")
	void isAttackForwardWhite(String source, String target, boolean expected) {
		Piece piece = new King(Side.WHITE, Position.of(source));
		assertThat(piece.isAttackForward(Position.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,true", "c3,a1,true", "c5,c4,true", "a3,a8,false", "d6,a1,true"})
	@DisplayName("진영별 공격방향인지 확인하는 기능 테스트 - 흑")
	void isAttackForwardBlack(String source, String target, boolean expected) {
		Piece piece = new King(Side.BLACK, Position.of(source));
		assertThat(piece.isAttackForward(Position.of(target))).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"c3,c1,false", "c3,a1,false", "c5,c4,false", "a3,a3,true", "d6,a1,false"})
	@DisplayName("같은 포지션인지 확인")
	void isSamePosition(String source, String target, boolean expected) {
		Piece piece = new King(Side.BLACK, Position.of(source));
		assertThat(piece.isSamePosition(Position.of(target))).isEqualTo(expected);
	}

	@Test
	@DisplayName("같은 진영인지 확인")
	void isSameSide() {
		Piece piece = new King(Side.BLACK, Position.of("a1"));
		assertThat(piece.isSameSide(Side.BLACK)).isTrue();
	}

	@Test
	@DisplayName("같은 진영인지 확인 - piece간 비교")
	void testIsSameSide() {
		Piece piece1 = new King(Side.BLACK, Position.of("a1"));
		Piece piece2 = new Queen(Side.BLACK, Position.of("a2"));
		assertThat(piece1.isSameSide(piece2)).isTrue();
	}
}