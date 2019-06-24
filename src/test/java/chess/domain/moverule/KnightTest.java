package chess.domain.moverule;

import chess.domain.MoveRule;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

	@Test
	public void 나이트가_잘_움직이는지() {
        MoveRule moveRule = Knight.getInstance();
		Position origin = Position.of("1", "b");
		Position target = Position.of("3", "c");
        assertTrue(moveRule.isValidMove(origin, target));
	}

	@Test
	public void 나이트가_이동안할떄() {
        MoveRule moveRule = Knight.getInstance();
		Position origin = Position.of("1", "b");
		Position target = Position.of("2", "c");
        assertFalse(moveRule.isValidMove(origin, target));
	}
}