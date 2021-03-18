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

}