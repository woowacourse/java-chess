package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("파일의 차이를 구한다.")
    void subtractOrderTest() {
        File file = File.A;
        File other = File.E;

        int gap = file.subtractOrder(other);

        assertThat(gap).isEqualTo(-4);
    }

    @Test
    @DisplayName("파일의 순서에 따라 이동할 수 있다")
    void moveOrderByStepTest() {
        File fromFile = File.A;
        int step = 3;

        File toFile = fromFile.move(step);

        assertThat(toFile).isEqualTo(File.D);
        assertThat(toFile.subtractOrder(fromFile)).isEqualTo(step);
    }
}
