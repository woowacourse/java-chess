package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class DirectionTest {

    @Test
    void RIGHT는_오른쪽이면_참() {
        int fileDiff = 5;
        int rankDiff = 0;

        boolean actual = Direction.RIGHT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_RIGHT은_위쪽_우측_대각선이면_참() {
        int fileDiff = 5;
        int rankDiff = 5;

        boolean actual = Direction.UP_RIGHT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_RIGHT은_위쪽_우측이어도_대각선이_아니면_거짓() {
        int fileDiff = 5;
        int rankDiff = 1;

        boolean actual = Direction.UP_RIGHT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isFalse();
    }

    @Test
    void UP은_위쪽이면_참() {
        int fileDiff = 0;
        int rankDiff = 5;

        boolean actual = Direction.UP
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_LEFT은_위쪽_좌측_대각선이면_참() {
        int fileDiff = -5;
        int rankDiff = 5;

        boolean actual = Direction.UP_LEFT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void UP_LEFT은_위쪽_좌측이어도_대각선이_아니면_거짓() {
        int fileDiff = -5;
        int rankDiff = 2;

        boolean actual = Direction.UP_LEFT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isFalse();
    }

    @Test
    void LEFT은_왼쪽이면_참() {
        int fileDiff = -5;
        int rankDiff = 0;

        boolean actual = Direction.LEFT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }


    @Test
    void DOWN_LEFT은_아래쪽_좌측_대각선이면_참() {
        int fileDiff = -5;
        int rankDiff = -5;

        boolean actual = Direction.DOWN_LEFT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }


    @Test
    void DOWN_LEFT은_아래쪽_좌측이어도_대각선이_아니면_거짓() {
        int fileDiff = -5;
        int rankDiff = -2;

        boolean actual = Direction.DOWN_LEFT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isFalse();
    }

    @Test
    void DOWN은_아래쪽이면_참() {
        int fileDiff = 0;
        int rankDiff = -5;

        boolean actual = Direction.DOWN
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void DOWN_RIGHT은_아래쪽_우측_대각선이면_참() {
        int fileDiff = 5;
        int rankDiff = -5;

        boolean actual = Direction.DOWN_RIGHT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isTrue();
    }

    @Test
    void DOWN_RIGHT은_아래쪽_우측이어도_대각선이_아니면_거짓() {
        int fileDiff = 3;
        int rankDiff = -5;

        boolean actual = Direction.DOWN_RIGHT
                .hasAngleOf(fileDiff, rankDiff);

        assertThat(actual).isFalse();
    }

    @Test
    void 위치_변화가_없는_경우_무조건_거짓() {
        int fileDiff = 0;
        int rankDiff = 0;

        boolean actual = Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.hasAngleOf(fileDiff, rankDiff));

        assertThat(actual).isFalse();
    }
}