package chess.domain.moverule;

import chess.domain.MoveRule;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    private final MoveRule moveRule = Queen.getInstance();

	@Test
	public void 정상_대각선_이동_테스트() {
		Position origin = Position.of("1", "d");
		Position target = Position.of("4", "a");

        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 정상_세로_이동_테스트() {
		Position origin = Position.of("1", "d");
		Position target = Position.of("5", "d");

        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 정상_가로_이동_테스트() {
		Position origin = Position.of("1", "d");
		Position target = Position.of("1", "h");

        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 비정상_이동_테스트() {
		Position origin = Position.of("1", "d");
		Position target = Position.of("3", "c");

        assertFalse(moveRule.isValidMove(origin, target));
	}


}