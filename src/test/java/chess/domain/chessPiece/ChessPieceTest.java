package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessPiece.pieceType.King;

public class ChessPieceTest {
	@Test
	void of_NameOfPieceType_ReturnInstance() {
		ChessPiece chessPiece1 = ChessPiece.of(PieceColor.WHITE.convertName(King.NAME));
		ChessPiece chessPiece2 = ChessPiece.of(PieceColor.WHITE.convertName(King.NAME));

		assertThat(chessPiece1.equals(chessPiece2)).isTrue();
		assertThat(chessPiece1 == chessPiece2).isTrue();
	}
}
