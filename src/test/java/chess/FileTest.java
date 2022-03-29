package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @DisplayName("File을 이동한다.")
    @Test
    void add() {
        assertThat(A.add(1)).isEqualTo(B);
    }
}
