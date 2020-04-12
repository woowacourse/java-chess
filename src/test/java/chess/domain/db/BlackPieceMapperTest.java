package chess.domain.db;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.db.BlackPieceMapper;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
public class BlackPieceMapperTest {
	@DisplayName("블랙 룩 심볼과 이니셜 스테이트가 주어졌을 때 같은 상태를 가진 Pawn이 생성되는지 확인")
	@Test
	void mappingTest() {
		String pieceSymbol = "♜";
		String pieceState = "Initial";

		Piece piece = BlackPieceMapper.mappingBy(pieceSymbol, pieceState);

		assertThat(piece).isInstanceOf(Rook.class);
		assertThat(piece.getState().toString()).isEqualTo(pieceState);
		assertThat(piece.getSymbol()).isEqualTo(pieceSymbol);
	}
}
