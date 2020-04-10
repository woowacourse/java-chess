package chess.domain;

import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * class description
 *
 * @author hotheadfactory
 */
public class TurnTest {
    @Test
    synchronized void turnChangeTest() {
        Turn turn = new Turn(Team.WHITE);
        assertTrue(turn.isTurnOf(Team.WHITE));
        turn = turn.changeTurn();
        assertTrue(turn.isTurnOf(Team.BLACK));
        turn = turn.changeTurn();
        assertTrue(turn.isTurnOf(Team.WHITE));
        turn = turn.changeTurn();
        assertTrue(turn.isTurnOf(Team.BLACK));
    }
}
