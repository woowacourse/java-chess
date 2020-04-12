package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
	@ParameterizedTest
	@CsvSource(value = {"b3,false", "c6,false", "a4,true", "f4,true", "e6,false", "b2,true", "e7,false"})
	@DisplayName("갈 수 있는 지 확인")
	void canMove(String target, boolean expected) {
		Piece piece = new Queen(Side.WHITE, Position.of("d4"));
		assertThat(piece.canMove(Position.of(target))).isEqualTo(expected);
	}
}