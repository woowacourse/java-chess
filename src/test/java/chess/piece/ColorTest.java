package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @Test
    @DisplayName("블랙 색상일 경우 화이트로 변환한다.")
    void reversBlack() {
        assertThat(Color.BLACK.reverse()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("화이트 색상일 경우 블랙으로 변환한다.")
    void reversWhite() {
        assertThat(Color.WHITE.reverse()).isEqualTo(Color.BLACK);
    }
}
