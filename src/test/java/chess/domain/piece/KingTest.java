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
class KingTest {
	@DisplayName("말이 죽어있을 때 movingTrace를 호출하면 예외처리")
	@Test
	void movingTraceTest() {
		King king = new King(new Captured(), "k");

		Position source = Position.of("a1");
		Position target = Position.of("a1");

		assertThatThrownBy(() -> king.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("죽은 말은");
	}

	@DisplayName("왕이 source에서 target으로 갈 수 없는 경우 예외처리")
	@Test
	void movingTraceTest2() {
		King king = new King(new Initial(), "k");

		Position source = Position.of("a1");
		Position target = Position.of("c1");

		assertThatThrownBy(() -> king.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는");
	}

	@DisplayName("왕이 source에서 target으로 정상적으로 갈 수 있는지 확인")
	@Test
	void movingTraceTest3() {
		King king = new King(new Initial(), "k");

		Position source = Position.of("a1");
		Position target = Position.of("b1");

		assertThat(king.movingTrace(source, target).size()).isEqualTo(2);
	}

	@DisplayName("폰인지 물어보면 false 반환하는지 확인")
	@Test
	void isPawnTest() {
		assertThat(new King(new Initial(), "k").isPawn()).isFalse();
	}

	@DisplayName("왕인지 물어보면 true를 반환하는지 확인")
	@Test
	void isKingTest() {
		assertThat((new King(new Initial(), "k").isKing())).isTrue();
	}

	@DisplayName("점수를 물어보면 0D를 반환하는지 확인")
	@Test
	void scoreTest() {
		assertThat(new King(new Initial(), "k").score()).isEqualTo(0D);
	}
}