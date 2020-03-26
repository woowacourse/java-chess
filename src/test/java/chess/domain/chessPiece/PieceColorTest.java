package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessPiece.pieceType.King;

public class PieceColorTest {
	@Test
	void convertName_PieceTypeName_ConvertByPieceColor() {
		assertThat(PieceColor.BLACK.convertName(King.NAME)).isEqualTo("K");
		assertThat(PieceColor.WHITE.convertName(King.NAME)).isEqualTo("k");
	}
}

