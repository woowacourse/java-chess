package chess.domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("파일의 차이를 구한다.")
    void subtractOrderTest() {
        File file = File.A;
        File other = File.E;

        int gap = file.subtractOrder(other);

        Assertions.assertThat(gap).isEqualTo(-4);
    }
}
