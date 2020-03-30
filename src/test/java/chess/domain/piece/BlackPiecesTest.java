package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class BlackPiecesTest {
	private BlackPieces blackPieces;

	@BeforeEach
	void setUp() {
		blackPieces = BlackPiecesFactory.create();
	}

	@DisplayName("없는 말을 움직이려고 할 때 예외처리")
	@Test
	void findTraceTest() {
		blackPieces.kill(Position.of("e8"));

		assertThatThrownBy(() -> blackPieces.findTrace(Position.of("e8"), Position.of("a1")))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("움직일 수");
	}

	@DisplayName("왕이 죽어 있을 때 true를 반환하는지 테스트")
	@Test
	void isKingDieTest() {
		blackPieces.kill(Position.of("e8"));

		assertThat(blackPieces.isKingDie()).isTrue();
	}

	@DisplayName("말을 움직였을 떄 source의 말이 없어지고 target으로 이동했는지 확인")
	@Test
	void moveFromToTest() {
		Position source = Position.of("a7");
		Position target = Position.of("a6");
		Piece sourcePiece = blackPieces.getPiece(source);

		blackPieces.moveFromTo(source, target);

		assertThat(blackPieces.getPiece(source)).isNull();
		assertThat(blackPieces.getPiece(target)).isEqualTo(sourcePiece);
	}

	@DisplayName("죽였을 때 죽는지 확인")
	@Test
	void killTest() {
		Position target = Position.of("e8");

		blackPieces.kill(target);

		assertThat(blackPieces.getPiece(target)).isNull();
	}

	@DisplayName("e8위치의 피스를 가져올 때 왕인지 확인")
	@Test
	void getPieceTest() {
		assertThat(blackPieces.getPiece(Position.of("e8")))
			.isInstanceOf(King.class);
	}
}