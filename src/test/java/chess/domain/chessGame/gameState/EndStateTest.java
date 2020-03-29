package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class EndStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void EndState_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new EndState(pieceColor)).isInstanceOf(EndState.class);
	}

}