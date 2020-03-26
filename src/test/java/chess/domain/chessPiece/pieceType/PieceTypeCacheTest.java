package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class PieceTypeCacheTest {
	@Test
	void from_KeyOfPieceType_ReturnInstance() {
		assertThat(PieceTypeCache.from("P")).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@NullSource
	void from_NullKey_ExceptionThrown(String key) {
		assertThatThrownBy(() -> PieceTypeCache.from(key))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 피스 타입의 key가 null입니다.");
	}

	@Test
	void from_InvalidKey_ExceptionThrown() {
		assertThatThrownBy(() -> PieceTypeCache.from("O"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 체스 피스 타입입니다.");
	}
}