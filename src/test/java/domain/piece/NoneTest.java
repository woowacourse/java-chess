package domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoneTest {

    @Test
    @DisplayName("비어있는 칸을 움직이려고 하면 예외가 발생한다")
    void moveNone() {
        final Piece none = new None(Color.NONE, Type.NONE);

        assertAll(
                () -> Assertions.assertThatThrownBy(none::strategy).isInstanceOf(UnsupportedOperationException.class),
                () -> Assertions.assertThatThrownBy(none::movableDirections)
                        .isInstanceOf(UnsupportedOperationException.class)
        );
    }
}
