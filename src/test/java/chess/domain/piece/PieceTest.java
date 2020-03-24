package chess.domain.piece;

import static chess.domain.Side.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class PieceTest {
	@DisplayName("생성자를 호출한 경우 piece 인스턴스 생성")
	@Test
	void construct() {
		assertThat(new Piece(BLACK, new Position(1, 1))).isInstanceOf(Piece.class);
	}
}