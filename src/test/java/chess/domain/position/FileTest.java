package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    @DisplayName("File에 a~h이 포함되는지 확인한다.")
    void contain() {
        assertThat(File.values())
            .containsOnly(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H);
    }

}
