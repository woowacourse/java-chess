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
class KnightTest {
	@DisplayName("말이 죽어있을 때 movingTrace를 호출하면 예외처리")
	@Test
	void movingTraceTest() {
		Piece knight = new Knight(new Captured(), "n");

		Position source = Position.of("a1");
		Position target = Position.of("a1");

		assertThatThrownBy(() -> knight.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("죽은 말은");
	}

	@DisplayName("나이트가 source에서 target으로 갈 수 없는 경우 예외처리")
	@Test
	void movingTraceTest2() {
		Piece knight = new Knight(new Initial(), "n");

		Position source = Position.of("a1");
		Position target = Position.of("c1");

		assertThatThrownBy(() -> knight.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는");
	}

	@DisplayName("나이트가 source에서 target으로 정상적으로 갈 수 있는지 확인")
	@Test
	void movingTraceTest3() {
		Piece knight = new Knight(new Initial(), "n");

		Position source = Position.of("a1");
		Position target = Position.of("b3");

		assertThat(knight.movingTrace(source, target).size()).isEqualTo(2);
	}

	@DisplayName("폰인지 물어보면 false 반환하는지 확인")
	@Test
	void isPawnTest() {
		assertThat(new Knight(new Initial(), "n").isPawn()).isFalse();
	}

	@DisplayName("왕인지 물어보면 false 반환하는지 확인")
	@Test
	void isKingTest() {
		assertThat((new Knight(new Initial(), "n").isKing())).isFalse();
	}

	@DisplayName("점수를 물어보면 2.5D를 반환하는지 확인")
	@Test
	void scoreTest() {
		assertThat(new Knight(new Initial(), "n").score()).isEqualTo(2.5D);
	}
}