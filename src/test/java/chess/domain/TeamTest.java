package chess.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    @DisplayName("같은 team이면 true를 반환한다.")
    void isAlly() {
        Team white = Team.WHITE;
        assertTrue(white.isSame(Team.WHITE));
    }

    @Test
    @DisplayName("다른 team이면 false를 반환한다.")
    void isNotAlly() {
        Team white = Team.WHITE;
        assertFalse(white.isSame(Team.BLACK));
    }
}
