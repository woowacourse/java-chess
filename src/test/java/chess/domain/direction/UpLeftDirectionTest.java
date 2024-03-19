package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpLeftDirectionTest {

    @Test
    @DisplayName("대각선 상좌 방향은 row 값이 최대이거나 column의 값이 최소일 경우 반환하지 않는다.")
    void notMove() {
        UpLeftDirection upLeftDirection = new UpLeftDirection();
        assertAll(
                () -> assertThat(upLeftDirection.move(new Position(8, 3))).isEmpty(),
                () -> assertThat(upLeftDirection.move(new Position(3, 1))).isEmpty()
        );
    }
}
