package chess.domain.pieces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {

    @Test
    @DisplayName("빈공간은 움직이면 에러를 발생시킨다.")
    void throws_exceptions_when_place_move() {
        // given
        Empty empty = new Empty();

        // when & then
        Assertions.assertThatThrownBy(
                () -> empty.canMove("c3", "c4")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
