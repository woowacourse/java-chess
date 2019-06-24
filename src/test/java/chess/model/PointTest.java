package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    void Point_동등_확인() {
        Point actual = new Point(3, 3);
        Point expected = new Point(3, 3);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Point_비동등_확인() {
        Point actual = new Point(3, 3);
        Point expected = new Point(4, 3);
        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    void source_target의_x값_차의_절대값_비교() {
        Point source = new Point(3, 3);
        Point target = new Point(2, 3);

        int actual = source.calculateXsDiff(target);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void source_target의_y값_차의_절대값_비교() {
        Point source = new Point(3, 7);
        Point target = new Point(2, 3);

        int actual = source.calculateYsDiff(target);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void N_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.N)).isEqualTo(new Point(3, 4));
    }

    @Test
    void NE_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.NE)).isEqualTo(new Point(4, 4));
    }

    @Test
    void E_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.E)).isEqualTo(new Point(4, 3));
    }

    @Test
    void SE_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.SE)).isEqualTo(new Point(4, 2));
    }

    @Test
    void S_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.S)).isEqualTo(new Point(3, 2));
    }

    @Test
    void SW_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.SW)).isEqualTo(new Point(2, 2));
    }

    @Test
    void W_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.W)).isEqualTo(new Point(2, 3));
    }

    @Test
    void NW_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.NW)).isEqualTo(new Point(2, 4));
    }

    @Test
    void UNDEFINED_움직임() {
        Point source = new Point(3, 3);
        assertThat(source.moveOneStep(Direction.UNDEFINED)).isEqualTo(new Point(3, 3));
    }
}
