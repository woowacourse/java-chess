package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("(1, 1) -> (2, 1)")
    void findMovablePositions() {
        King king = new King(new Position(1, 1), Color.WHITE);
        Position destination = new Position(2, 1);

        assertThat(king.findMovablePositions(destination)).contains(destination);
    }
}