package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PieceTypeTest {

	@Test
	void isSame_NotSamePieceColor_ReturnFalse() {
		assertThat(PieceType.BLACK_BISHOP.isSame(PieceColor.WHITE)).isFalse();
	}

	@Test
	void isSame_SamePieceColor_ReturnTrue() {
		assertThat(PieceType.BLACK_BISHOP.isSame(PieceColor.BLACK)).isTrue();
	}

	@Test
	void isSame_NotSamePieceType_ReturnFalse() {
		assertThat(PieceType.BLACK_BISHOP.isSamePieceColor(PieceType.WHITE_BISHOP)).isFalse();
	}

	@Test
	void isSame_SamePieceType_ReturnTrue() {
		assertThat(PieceType.BLACK_BISHOP.isSamePieceColor(PieceType.BLACK_KING)).isTrue();
	}

	@ParameterizedTest
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"BLACK_PAWN", "WHITE_PAWN"}, value = PieceType.class)
	void isPawn_NotPawnPieceType_ReturnFalse(final PieceType pieceType) {
		assertThat(pieceType.isPawn()).isFalse();
	}

	@ParameterizedTest
	@EnumSource(names = {"BLACK_PAWN", "WHITE_PAWN"}, value = PieceType.class)
	void isPawn_PawnPieceType_ReturnTrue(final PieceType pieceType) {
		assertThat(pieceType.isPawn()).isTrue();
	}

	@ParameterizedTest
	@EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"BLACK_KING", "WHITE_KING"}, value = PieceType.class)
	void isKing_NotKingPieceType_ReturnFalse(final PieceType pieceType) {
		assertThat(pieceType.isKing()).isFalse();
	}

	@ParameterizedTest
	@EnumSource(names = {"BLACK_KING", "WHITE_KING"}, value = PieceType.class)
	void isKing_KingPieceType_ReturnTrue(final PieceType pieceType) {
		assertThat(pieceType.isKing()).isTrue();
	}

}