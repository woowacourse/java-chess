package chess.domain.chessboard;

import chess.domain.chessPiece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

class ChessBoardFactoryTest {
	@Test
	@DisplayName("체스판 생성 테스트")
	private void createChessBoardTest() {
		List<Position> chessBoard = ChessBoardFactory.create();
		assertThat(chessBoard.size()).isEqualTo(64);
	}
}
