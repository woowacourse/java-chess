package domain.piece.info;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("두 위치가 같으면 예외가 발생한다")
    void isSamePosition() {
        final Position position = new Position(File.A, Rank.ONE);
        Assertions.assertThatThrownBy(() -> new Vector(position, position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 위치가 같습니다");
    }


    /*
        T.....T.  7
        .x...x..  6
        ..x.x...  5
        ...S....  4
        ..x.x...  3
        .x...x..  2
        T.....T.  1 (rank 1)

        abcdefgh
     */
    @Test
    @DisplayName("주어진 두 위치가 대각선상에 위치하는지 확인한다")
    void isDiagonal() {
        final Position source = new Position(File.D, Rank.FOUR);
        final Position targetLeftUp = new Position(File.A, Rank.SEVEN);
        final Position targetRightUp = new Position(File.G, Rank.SEVEN);
        final Position targetLeftDown = new Position(File.A, Rank.ONE);
        final Position targetRightDown = new Position(File.G, Rank.ONE);

        assertAll(
                () -> Assertions.assertThat(new Vector(source, targetLeftUp).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRightUp).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetLeftDown).isDiagonal()).isTrue(),
                () -> Assertions.assertThat(new Vector(source, targetRightDown).isDiagonal()).isTrue()
        );
    }
}
