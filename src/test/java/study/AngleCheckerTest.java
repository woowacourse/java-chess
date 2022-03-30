package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AngleCheckerTest {

    @Test
    void mathAtan2_horizontalOrVertical() {
        // Math.atan2(y, x)
        assertThat(Math.toDegrees(Math.atan2(0, 1))).isEqualTo(0);
        assertThat(Math.toDegrees(Math.atan2(1, 0))).isEqualTo(90);
        assertThat(Math.toDegrees(Math.atan2(0, -1))).isEqualTo(180);
        assertThat(Math.toDegrees(Math.atan2(-1, 0))).isEqualTo(-90);

    }
    @Test
    void mathAtan2_diagonal() {
        // Math.atan2(y, x)
        assertThat(Math.toDegrees(Math.atan2(1, 1))).isEqualTo(45);
        assertThat(Math.toDegrees(Math.atan2(1, -1))).isEqualTo(135);
        assertThat(Math.toDegrees(Math.atan2(-1, -1))).isEqualTo(-135);
        assertThat(Math.toDegrees(Math.atan2(-1, 1))).isEqualTo(-45);
    }
}
