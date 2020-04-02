package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.PieceScore;

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

	@DisplayName("Piece와 PieceScore에 있는 이름과 같은지 확인하는 테스트")
	@Test
	void isSameNameTest() {
		Piece piece = new King(Color.BLACK, "K");

		assertThat(piece.isSameName(PieceScore.KING)).isTrue();
	}

	@DisplayName("Piece가 Pawn인지 확인하는 테스트")
	@Test
	void isPawnTest() {
		Piece pawn = new Pawn(Color.BLACK, "P");
		Piece king = new King(Color.BLACK, "K");

		assertThat(pawn.isPawn()).isTrue();
		assertThat(king.isPawn()).isFalse();
	}

	@DisplayName("Piece가 King인지 확인하는 테스트")
	@Test
	void isKingTest() {
		Piece pawn = new Pawn(Color.BLACK, "P");
		Piece king = new King(Color.BLACK, "K");

		assertThat(pawn.isKing()).isFalse();
		assertThat(king.isKing()).isTrue();
	}
}
