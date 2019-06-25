package chess.domain.RuleImpl;

import chess.domain.Position;
import chess.domain.Rule;
import chess.domain.rule.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    Rule rule = Rook.getInstance();

    @Test
    void isValidMoveTest() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("8", "a");
        assertTrue(rule.isValidMove(origin, target));

    }
}