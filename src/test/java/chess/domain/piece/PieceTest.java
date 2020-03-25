package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class PieceTest {
	@Test
	void test() {
		assertThat(new Pawn("black")).isInstanceOf(Piece.class);
	}
}
