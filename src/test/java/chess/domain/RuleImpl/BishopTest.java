package chess.domain.RuleImpl;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {
	Rule rule = Bishop.getInstance();

	@Test
	public void 정상_이동_테스트() {
		Position origin = Position.of("1", "f");
		Position target = Position.of("6", "a");

		assertTrue(rule.isValidMove(origin, target));
	}

	@Test
	public void 비정상_이동_테스트() {
		Position origin = Position.of("1", "f");
		Position target = Position.of("2", "f");

		assertFalse(rule.isValidMove(origin, target));
	}
}