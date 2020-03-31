package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
	@ParameterizedTest
	@CsvSource(value = {"b3,true", "c6,true", "a4,false", "f4,false", "e6,true", "b2,false", "c4,false"})
	@DisplayName("갈 수 있는 지 확인")
	void canMove(String target, boolean expected) {
		Piece piece = new Knight(Side.WHITE, new Position("d4"));
		assertThat(piece.canMove(new Position(target))).isEqualTo(expected);
	}
}