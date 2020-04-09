package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
	@ParameterizedTest
	@CsvSource(value = {"b3,false", "d6,false", "c4,false", "d5,true", "e6,false", "b2,false", "c4,false"})
	@DisplayName("갈 수 있는 지 확인")
	void canMove(String target, boolean expected) {
		Piece piece = new Pawn(Side.WHITE, Position.of("d4"));
		assertThat(piece.canMove(Position.of(target))).isEqualTo(expected);
	}

	@Test
	@DisplayName("처음 위치에 있을 시 2칸 전진 기능 테스트")
	void canMoveInit() {
		Piece piece = new Pawn(Side.WHITE, Position.of("d2"));
		assertThat(piece.canMove(Position.of("d4"))).isTrue();
	}

	@ParameterizedTest
	@MethodSource(value = "providePiece")
	@DisplayName("공격할 수 있는지 확인")
	void canAttack(Piece target, boolean expected) {
		Piece piece = new Pawn(Side.WHITE, Position.of("d4"));
		assertThat(piece.canAttack(target)).isEqualTo(expected);
	}

	private static Stream<Arguments> providePiece() {
		return Stream.of(
				Arguments.of(new Pawn(Side.WHITE, Position.of("d5")), false),
				Arguments.of(new Pawn(Side.WHITE, Position.of("e5")), false),
				Arguments.of(new Pawn(Side.WHITE, Position.of("c5")), false),
				Arguments.of(new Pawn(Side.BLACK, Position.of("d5")), false),
				Arguments.of(new Pawn(Side.BLACK, Position.of("e5")), true),
				Arguments.of(new Pawn(Side.BLACK, Position.of("c5")), true));
	}
}