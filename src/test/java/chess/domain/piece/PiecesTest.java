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
public class PiecesTest {
	private Pieces whitePieces;

	@BeforeEach
	void setUp() {
		whitePieces = WhitePiecesFactory.create();
	}

	@DisplayName("없는 말을 움직이려고 할 때 예외처리")
	@Test
	void findTraceTest() {
		whitePieces.kill(Position.of("e1"));

		assertThatThrownBy(() -> whitePieces.findTrace(Position.of("e1"), Position.of("a1")))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("움직일 수");
	}

	@DisplayName("왕이 죽어 있을 때 true를 반환하는지 테스트")
	@Test
	void isKingDieTest() {
		whitePieces.kill(Position.of("e1"));

		assertThat(whitePieces.isKingDie()).isTrue();
	}

	@DisplayName("말을 움직였을 떄 source의 말이 없어지고 target으로 이동했는지 확인")
	@Test
	void moveFromToTest() {
		Position source = Position.of("a2");
		Position target = Position.of("a3");
		Piece sourcePiece = whitePieces.getPiece(source);

		whitePieces.moveFromTo(source, target);

		assertThat(whitePieces.getPiece(source)).isNull();
		assertThat(whitePieces.getPiece(target)).isEqualTo(sourcePiece);
	}

	@DisplayName("죽였을 때 죽는지 확인")
	@Test
	void killTest() {
		Position target = Position.of("e1");

		whitePieces.kill(target);

		assertThat(whitePieces.getPiece(target)).isNull();
	}

	@DisplayName("e1위치의 피스를 가져올 때 왕인지 확인")
	@Test
	void getPieceTest() {
		assertThat(whitePieces.getPiece(Position.of("e1")))
			.isInstanceOf(King.class);
	}
}
