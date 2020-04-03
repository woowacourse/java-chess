package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class EmptyTest {
	private Piece empty = Empty.EMPTY;

	@DisplayName("빈칸 객체에서 해당 메서드 호출시 USO 예외 발생")
	@Test
	void findMoveModeTraceTest() {
		assertThatThrownBy(() -> empty.findMoveModeTrace(Position.of("a1"), Position.of("b2")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("빈칸 객체에서 해당 메서드 호출시 USO 예외 발생")
	@Test
	void findCatchModeTraceTest() {
		assertThatThrownBy(() -> empty.findCatchModeTrace(Position.of("a1"), Position.of("b2")))
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@DisplayName("빈칸에 대한 심볼로 . 반환")
	@Test
	void getInitialCharacterTest() {
		String actual = empty.getSymbol();
		assertThat(actual).isEqualTo(".");
	}

	@DisplayName("빈칸 객체에서 해당 메서드 호출시 USO 예외 발생")
	@Test
	void getScoreTest() {
		assertThatThrownBy(() -> empty.getScore())
			.isInstanceOf(UnsupportedOperationException.class);
	}
}