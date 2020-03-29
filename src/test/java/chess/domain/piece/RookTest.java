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
class RookTest {
	@DisplayName("말이 죽어있을 때 movingTrace를 호출하면 예외처리")
	@Test
	void movingTraceTest() {
		Piece rook = new Rook(new Captured(), "n");

		Position source = Position.of("a1");
		Position target = Position.of("a1");

		assertThatThrownBy(() -> rook.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("죽은 말은");
	}

	@DisplayName("룩이 source에서 target으로 갈 수 없는 경우 예외처리")
	@Test
	void movingTraceTest2() {
		Piece rook = new Rook(new Initial(), "r");

		Position source = Position.of("a1");
		Position target = Position.of("c2");

		assertThatThrownBy(() -> rook.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는");
	}

	@DisplayName("룩이 source에서 target으로 정상적으로 갈 수 있는지 확인")
	@Test
	void movingTraceTest3() {
		Piece rook = new Rook(new Initial(), "r");

		Position source = Position.of("a1");
		Position target = Position.of("a8");

		assertThat(rook.movingTrace(source, target).size()).isEqualTo(8);
	}

	@DisplayName("폰인지 물어보면 false 반환하는지 확인")
	@Test
	void isPawnTest() {
		assertThat(new Rook(new Initial(), "r").isPawn()).isFalse();
	}
}