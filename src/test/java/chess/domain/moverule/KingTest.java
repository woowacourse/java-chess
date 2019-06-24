package chess.domain.moverule;

import chess.domain.MoveRule;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {
    private MoveRule moveRule = King.getInstance();

	@Test
	public void 정상_이동_테스트() {
		Position origin = Position.of("1", "e");
		Position target = Position.of("1", "d");
        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 정상_대각선_이동_테스트() {
		Position origin = Position.of("1", "e");
		Position target = Position.of("2", "d");
        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 비정상_대각선_두칸_이동_테스트() {
		Position origin = Position.of("1", "e");
		Position target = Position.of("3", "c");
        assertFalse(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 비정상_수직_두칸_이동_테스트() {
		Position origin = Position.of("1", "e");
		Position target = Position.of("3", "e");
        assertFalse(moveRule.isValidMove(origin, target));
	}


}