package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        assertThatThrownBy(ypoint::up).isInstanceOf(IllegalArgumentException.class);
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

        assertThatThrownBy(ypoint::down).isInstanceOf(IllegalArgumentException.class);
    }
}
