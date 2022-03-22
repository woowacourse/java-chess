package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {
    @Test
    @DisplayName("파일는 8가지로 이루어져 있다.")
    void countRanks() {
        assertThat(File.values().length).isEqualTo(8);
    }
}
