package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class KingCaughtStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void KingCaughtState_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new KingCaughtState(pieceColor)).isInstanceOf(KingCaughtState.class);
	}

}