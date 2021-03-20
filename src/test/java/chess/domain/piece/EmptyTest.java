package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EmptyTest {
    @Test
    @DisplayName("Empty을 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Empty('a', '2');
            new Empty('a', 2);
            new Empty(new Position('a', '2'));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Empty의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Empty empty = new Empty('a', '2');
        Empty empty2 = new Empty('a', 2);
        Empty empty3 = new Empty(new Position('a', '2'));
        Position expectedPosition = new Position("a2");
        assertThat(empty.position()).isEqualTo(expectedPosition);
        assertThat(empty2.position()).isEqualTo(expectedPosition);
        assertThat(empty3.position()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("사용햐면 안 되는 메서드에 대해서 예외 발생")
    public void color() {
        Empty empty = new Empty('a', '2');
        assertThatThrownBy(() -> {
            empty.color();
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }

    @Test
    @DisplayName("사용햐면 안 되는 메서드에 대해서 예외 발생")
    public void directions() {
        Empty empty = new Empty('a', '2');
        assertThatThrownBy(() -> {
            empty.directions();
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }

    @Test
    @DisplayName("사용햐면 안 되는 메서드에 대해서 예외 발생")
    public void stepRange() {
        Empty empty = new Empty('a', '2');
        assertThatThrownBy(() -> {
            empty.stepRange();
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }

    @Test
    @DisplayName("사용햐면 안 되는 메서드에 대해서 예외 발생")
    public void score() {
        Empty empty = new Empty('a', '2');
        assertThatThrownBy(() -> {
            empty.score();
        }).isInstanceOf(UnsupportedOperationException.class).hasMessage("해당 메서드를 사용하면 안 됩니다.");
    }
}
