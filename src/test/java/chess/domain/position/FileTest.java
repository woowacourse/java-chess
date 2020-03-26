package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {
    @Test
    void getFileDifferenceTest() {
        assertThat(File.H.getFileDifference(File.A)).isEqualTo(-7);
    }
}
