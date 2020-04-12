package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
	@ParameterizedTest
	@CsvSource(value = {"d1,false", "d8,false", "a4,false", "f4,false", "c3,true", "b2,false", "c4,true"})
	@DisplayName("갈 수 있는 지 확인")
	void canMove(String target, boolean expected) {
		Piece piece = new King(Side.WHITE, Position.of("d4"));
		assertThat(piece.canMove(Position.of(target))).isEqualTo(expected);
	}
}