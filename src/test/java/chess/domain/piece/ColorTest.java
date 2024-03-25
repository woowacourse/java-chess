package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ColorTest {

    @DisplayName("검은색인지를 반환한다.")
    @Test
    void isBlack() {
        boolean isBlack1 = Color.BLACK.isBlack();
        boolean isBlack2 = Color.WHITE.isBlack();

        assertAll(
                () -> assertThat(isBlack1).isTrue(),
                () -> assertThat(isBlack2).isFalse()
        );
    }

    @DisplayName("흰색인지를 반환한다.")
    @Test
    void isWhite() {
        boolean isWhite1 = Color.BLACK.isWhite();
        boolean isWhite2 = Color.WHITE.isWhite();

        assertAll(
                () -> assertThat(isWhite1).isFalse(),
                () -> assertThat(isWhite2).isTrue()
        );
    }
}
