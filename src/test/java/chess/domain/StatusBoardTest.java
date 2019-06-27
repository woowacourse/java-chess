package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StatusBoardTest {

    @Test
    void 점수_오류() {
        int whiteScore = 77;
        int blackScore = 23;
        assertThrows(IllegalArgumentException.class, () -> {
            new StatusBoard(blackScore, whiteScore);
        });
    }
}