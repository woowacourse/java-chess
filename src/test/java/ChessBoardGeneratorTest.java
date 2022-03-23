import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardGeneratorTest {

    @Test
    @DisplayName("체스보드의 사이즈는 64이다.")
    void checkChessBoardSize() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();

        assertThat(chessBoardGenerator.generate().size()).isEqualTo(64);
    }
}
