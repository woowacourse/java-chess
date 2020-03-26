package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class PieceColorTest {
	@Test
	void convertName_PieceTypeName_ConvertByPieceColor() {
		assertThat(PieceColor.BLACK.convertName(King.NAME)).isEqualTo("K");
		assertThat(PieceColor.WHITE.convertName(King.NAME)).isEqualTo("k");
	}

	@ParameterizedTest
	@NullSource
	void convertName_NullPieceTypeName_ExceptionThrown(String name) {
		assertThatThrownBy(() -> PieceColor.BLACK.convertName(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 이름이 유효하지 않습니다.");
	}
}

