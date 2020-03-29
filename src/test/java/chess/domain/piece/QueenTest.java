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
class QueenTest {
	@DisplayName("말이 죽어있을 때 movingTrace를 호출하면 예외처리")
	@Test
	void movingTraceTest() {
		Piece queen = new Queen(new Captured(), "q");

		Position source = Position.of("a1");
		Position target = Position.of("a1");

		assertThatThrownBy(() -> queen.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("죽은 말은");
	}

	@DisplayName("퀸이 source에서 target으로 갈 수 없는 경우 예외처리")
	@Test
	void movingTraceTest2() {
		Piece queen = new Queen(new Initial(), "q");

		Position source = Position.of("a1");
		Position target = Position.of("c2");

		assertThatThrownBy(() -> queen.movingTrace(source, target))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는");
	}

	@DisplayName("퀸이 source에서 target으로 정상적으로 갈 수 있는지 확인")
	@Test
	void movingTraceTest3() {
		Piece queen = new Queen(new Initial(), "q");

		Position source = Position.of("a1");
		Position target = Position.of("h8");

		assertThat(queen.movingTrace(source, target).size()).isEqualTo(8);
	}
}