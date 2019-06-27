package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    Point source;
    Point target;

    @Test
    void N_테스트() {
        source = new Point(3, 3);
        target = new Point(3, 8);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.N);
    }

    @Test
    void NE_테스트() {
        source = new Point(3, 3);
        target = new Point(8, 8);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.NE);
    }

    @Test
    void E_테스트() {
        source = new Point(3, 3);
        target = new Point(8, 3);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.E);
    }

    @Test
    void SE_테스트() {
        source = new Point(3, 3);
        target = new Point(5, 1);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.SE);
    }

    @Test
    void S_테스트() {
        source = new Point(3, 3);
        target = new Point(3, 1);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.S);
    }

    @Test
    void SW_테스트() {
        source = new Point(3, 3);
        target = new Point(1, 1);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.SW);
    }

    @Test
    void W_테스트() {
        source = new Point(3, 3);
        target = new Point(1, 3);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.W);
    }

    @Test
    void NW_테스트() {
        source = new Point(3, 3);
        target = new Point(1, 5);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.NW);
    }

    @Test
    void UNDEFINED_테스트() {
        source = new Point(3, 3);
        target = new Point(4, 5);
        assertThat(Direction.valueOf(source, target)).isEqualTo(Direction.UNDEFINED);
    }
}

