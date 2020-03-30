package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class PieceTest {

	@DisplayName("Piece의 색깔과 같은 색깔인지 확인하는 테스트")
	@Test
	void isSameColorTest() {
		Piece piece = new King(Color.BLACK, "K");

		assertThat(piece.isSameColor(Color.BLACK)).isTrue();
		assertThat(piece.isSameColor(Color.WHITE)).isFalse();
	}
}
