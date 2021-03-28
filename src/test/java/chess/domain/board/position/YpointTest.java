package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class YpointTest {
    @Test
    @DisplayName("Ypoint 출력을 위한 값 반환 테스트")
    void testPrintYpoint() {
        assertThat(Ypoint.ONE.getValue()).isEqualTo(1);
        assertThat(Ypoint.TWO.getValue()).isEqualTo(2);
        assertThat(Ypoint.THREE.getValue()).isEqualTo(3);
        assertThat(Ypoint.FOUR.getValue()).isEqualTo(4);
        assertThat(Ypoint.FIVE.getValue()).isEqualTo(5);
        assertThat(Ypoint.SIX.getValue()).isEqualTo(6);
        assertThat(Ypoint.SEVEN.getValue()).isEqualTo(7);
        assertThat(Ypoint.EIGHT.getValue()).isEqualTo(8);
    }

    @Test
    @DisplayName("ypoint up 테스트")
    void upTest() {
        Ypoint ypoint = Ypoint.FIVE;

        ypoint = ypoint.up();
        assertThat(ypoint).isEqualTo(Ypoint.SIX);

        ypoint = ypoint.up();
        assertThat(ypoint).isEqualTo(Ypoint.SEVEN);

        ypoint = ypoint.up();
        assertThat(ypoint).isEqualTo(Ypoint.EIGHT);

        ypoint = ypoint.up();
        assertThat(ypoint).isEqualTo(Ypoint.EIGHT);
    }

    @Test
    @DisplayName("ypoint 복수형 up 테스트")
    void integerUpTest() {
        Ypoint ypoint = Ypoint.FIVE;

        ypoint = ypoint.up(2);
        assertThat(ypoint).isEqualTo(Ypoint.SEVEN);

        ypoint = ypoint.up(1);
        assertThat(ypoint).isEqualTo(Ypoint.EIGHT);

        ypoint = ypoint.up(3);
        assertThat(ypoint).isEqualTo(Ypoint.EIGHT);

        ypoint = ypoint.up(1);
        assertThat(ypoint).isEqualTo(Ypoint.EIGHT);
    }

    @Test
    @DisplayName("ypoint down 테스트")
    void downTest() {
        Ypoint ypoint = Ypoint.FOUR;

        ypoint = ypoint.down();
        assertThat(ypoint).isEqualTo(Ypoint.THREE);

        ypoint = ypoint.down();
        assertThat(ypoint).isEqualTo(Ypoint.TWO);

        ypoint = ypoint.down();
        assertThat(ypoint).isEqualTo(Ypoint.ONE);

        ypoint = ypoint.down();
        assertThat(ypoint).isEqualTo(Ypoint.ONE);
    }

    @Test
    @DisplayName("ypoint 복수형 down 테스트")
    void integerDownTest() {
        Ypoint ypoint = Ypoint.FIVE;

        ypoint = ypoint.down(2);
        assertThat(ypoint).isEqualTo(Ypoint.THREE);

        ypoint = ypoint.down(2);
        assertThat(ypoint).isEqualTo(Ypoint.ONE);

        ypoint = ypoint.down(3);
        assertThat(ypoint).isEqualTo(Ypoint.ONE);

        ypoint = ypoint.down(1);
        assertThat(ypoint).isEqualTo(Ypoint.ONE);
    }
}
