package chess.move;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.Board;

class MoveValidStrategyTest {
	@DisplayName("MoveValidStrategy 객체 생성 테스트")
	@Test
	void constructTest() {
		MoveValidateStrategy moveValidateStrategy = new RookMoveValidateStrategy(new Board(null));
		assertThat(moveValidateStrategy).isInstanceOf(RookMoveValidateStrategy.class);
	}
}