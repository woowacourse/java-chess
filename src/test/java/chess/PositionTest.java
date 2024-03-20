package chess;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {
    @Test
    @DisplayName("Position을 생성한다.")
    void makePosition() {
        assertThatCode(() -> {
            Position.of("b2");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("잘못된 Position을 넘기면 에러가 발생한다.")
    void wrongValueTest () {
       assertThatThrownBy(()->{
           Position.of("x5");
       }).isInstanceOf(IllegalArgumentException.class);
    }
}
