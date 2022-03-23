package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        Blank blank = new Blank();
        assertThat(blank.isBlank()).isTrue();
    }
}