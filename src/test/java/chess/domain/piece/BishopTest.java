package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.PieceScore;

class BishopTest {

	@DisplayName("Bishop 클래스가 PieceScore에 있는 Enum Bishop과 동일한지 테스트")
	@Test
	void isSameName() {
		Bishop bishop = new Bishop(Color.WHITE, "b");
		assertThat(bishop.isSameName(PieceScore.BISHOP)).isTrue();
	}

	// @DisplayName("해당 말의 룰에 따라 갈 수 있는 경우를 모두 반환할 때 그 사이즈를 체크하는 테스트")
	// @Test
	// void movablePositions() {
	// 	Map<Position, Piece> originPiece = new HashMap<>();
	// 	BlankPieceFactory.create(originPiece);
	// 	Pieces pieces = new Pieces(originPiece);
	// 	pieces.addPiece(Position.of("a1"), new Bishop(Color.WHITE, "b"));
	// 	pieces.addPiece(Position.of("d4"), new Bishop(Color.WHITE, "b"));
	//
	// 	Piece piece = pieces.getPieceByPosition(Position.of("a1"));
	// 	List<Position> positions = piece.movablePositions(Position.of("a1"), originPiece);
	//
	// 	assertThat(positions.size()).isEqualTo(4);
	//
	// }
}