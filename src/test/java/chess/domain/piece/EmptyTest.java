package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmptyTest {
    @Test
    @DisplayName("Empty 인스턴스를 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Empty('a', '1');
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("move 메서드를 사용하면 예외 발생")
    public void move() {
        Empty empty = new Empty('a', '1');
        assertThatThrownBy(empty::move)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }


    @Test
    @DisplayName("movable 메서드를 사용하면 예외 발생")
    public void isMovable() {
        Empty empty = new Empty('a', '1');
        assertThatThrownBy(empty::isMovable)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }
}
