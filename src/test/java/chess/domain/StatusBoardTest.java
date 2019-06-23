package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatusBoardTest {
    @Test
    void 초기_점수_계산() {
        int whiteScore = 34;
        int blackScore = 23;
        StatusBoard statusBoard = new StatusBoard(blackScore, whiteScore);
        assertThat(statusBoard.getBlackScore()).isEqualTo(23);
    }

    @Test
    void 점수_오류() {
        int whiteScore = 77;
        int blackScore = 23;
        assertThrows(IllegalArgumentException.class, () -> {
            new StatusBoard(blackScore, whiteScore);
        });
    }
}