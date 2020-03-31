package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Position;
import chess.domain.piece.state.Captured;
import chess.domain.piece.state.Initial;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class BishopTest {
	@DisplayName("말이 죽어있을 때 movingTrace를 호출하면 예외처리")
	@Test
	void movingTraceTest() {
		Piece bishop = new Bishop(new Captured(), "b");

		Position source = Position.of("a1");
		Position target = Position.of("a1");

		assertThatThrownBy(() -> bishop.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("죽은 말은");
	}

	@DisplayName("비숍이 source에서 target으로 갈 수 없는 경우 예외처리")
	@Test
	void movingTraceTest2() {
		Piece bishop = new Bishop(new Initial(), "b");

		Position source = Position.of("a1");
		Position target = Position.of("c2");

		assertThatThrownBy(() -> bishop.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는");
	}

	@DisplayName("비숍이 source에서 target으로 정상적으로 갈 수 있는지 확인")
	@Test
	void movingTraceTest3() {
		Piece bishop = new Bishop(new Initial(), "b");

		Position source = Position.of("a1");
		Position target = Position.of("h8");

		assertThat(bishop.movingTrace(source, target).size()).isEqualTo(8);
	}

	@DisplayName("폰인지 물어보면 false 반환하는지 확인")
	@Test
	void isPawnTest() {
		assertThat(new Bishop(new Initial(), "b").isPawn()).isFalse();
	}

	@DisplayName("왕인지 물어보면 false 반환하는지 확인")
	@Test
	void isKingTest() {
		assertThat((new Bishop(new Initial(), "b").isKing())).isFalse();
	}

	@DisplayName("점수를 물어보면 3D를 반환하는지 확인")
	@Test
	void scoreTest() {
		assertThat(new Bishop(new Initial(), "b").score()).isEqualTo(3D);
	}
}