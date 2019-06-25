package chess.domain.RuleImpl;

import chess.domain.Position;
import chess.domain.Rule;
import chess.domain.rule.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    Rule rule = Queen.getInstance();

    @Test
    public void 정상_대각선_이동_테스트() {
        Position origin = Position.of("1", "d");
        Position target = Position.of("4", "a");

        assertTrue(rule.isValidMove(origin, target));
    }

    @Test
    public void 정상_세로_이동_테스트() {
        Position origin = Position.of("1", "d");
        Position target = Position.of("5", "d");

        assertTrue(rule.isValidMove(origin, target));
    }

    @Test
    public void 정상_가로_이동_테스트() {
        Position origin = Position.of("1", "d");
        Position target = Position.of("1", "h");

        assertTrue(rule.isValidMove(origin, target));
    }

    @Test
    public void 비정상_이동_테스트() {
        Position origin = Position.of("1", "d");
        Position target = Position.of("3", "c");

        assertFalse(rule.isValidMove(origin, target));
    }


}