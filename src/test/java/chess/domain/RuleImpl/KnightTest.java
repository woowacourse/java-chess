package chess.domain.RuleImpl;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

	@Test
	public void 나이트가_잘_움직이는지() {
		Rule rule = Knight.getInstance();
		Position origin = Position.of("1", "b");
		Position target = Position.of("3", "c");
		assertTrue(rule.isValidMove(origin, target));
	}

	@Test
	public void 나이트가_이동안할떄() {
		Rule rule = Knight.getInstance();
		Position origin = Position.of("1", "b");
		Position target = Position.of("2", "c");
		assertFalse(rule.isValidMove(origin, target));
	}
}