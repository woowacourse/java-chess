package chess.domain.chessBoard;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChessBoardTest {
	@Test
	void ChessBoard_MapOfPositionAndChessPiece_GenerateInstance() {
		assertThat(new ChessBoard(ChessBoardInitializer.create())).isInstanceOf(ChessBoard.class);
	}
}
