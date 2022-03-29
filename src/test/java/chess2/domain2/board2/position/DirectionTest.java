package chess2.domain2.board2.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class DirectionTest {

    @Test
    void UP은_위쪽이면_참() {
        boolean actual = Direction.UP
                .checkByPositionDifference(0, 5);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_RIGHT은_위쪽_우측이면_무조건_참() {
        boolean actual = Direction.UP_RIGHT
                .checkByPositionDifference(1, 5);

        assertThat(actual).isTrue();
    }

    @Test
    void RIGHT는_오른쪽이면_참() {
        boolean actual = Direction.RIGHT
                .checkByPositionDifference(5, 0);

        assertThat(actual).isTrue();
    }

    @Test
    void DOWN_RIGHT은_아래쪽_우측이면_무조건_참() {
        boolean actual = Direction.DOWN_RIGHT
                .checkByPositionDifference(5, -1);

        assertThat(actual).isTrue();
    }

    @Test
    void DOWN은_아래쪽이면_참() {
        boolean actual = Direction.DOWN
                .checkByPositionDifference(0, -5);

        assertThat(actual).isTrue();
    }

    @Test
    void DOWN_LEFT은_아래쪽_좌측이면_무조건_참() {
        boolean actual = Direction.DOWN_LEFT
                .checkByPositionDifference(-1, -5);

        assertThat(actual).isTrue();
    }

    @Test
    void LEFT은_왼쪽이면_참() {
        boolean actual = Direction.LEFT
                .checkByPositionDifference(-5, 0);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_LEFT은_위쪽_좌측이면_무조건_참() {
        boolean actual = Direction.UP_LEFT
                .checkByPositionDifference(-5, 5);

        assertThat(actual).isTrue();
    }
}