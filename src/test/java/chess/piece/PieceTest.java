package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

	@DisplayName("싱글톤 생성테스트")
	@Test
	void name() {
		assertThat(Piece.of(PieceType.KING, false)).isEqualTo(Piece.of(PieceType.KING, false));
	}

}