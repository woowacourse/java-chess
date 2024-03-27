package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    @DisplayName("블랙-화이트 또는 화이트-블랙의 색을 가진 경우 참을 반환한다.")
    void isOpposite_True() {
        assertAll(() -> {
            assertThat(Color.BLACK.isOpposite(Color.WHITE)).isTrue();
            assertThat(Color.WHITE.isOpposite(Color.BLACK)).isTrue();
        });
    }

    @Test
    @DisplayName("블랙-화이트 또는 화이트-블랙의 색이 아닌 경우 거짓을 반환한다.")
    void isOpposite_False() {
        assertAll(() -> {
            assertThat(Color.BLACK.isOpposite(Color.BLACK)).isFalse();
            assertThat(Color.BLACK.isOpposite(Color.NONE)).isFalse();
            assertThat(Color.WHITE.isOpposite(Color.WHITE)).isFalse();
            assertThat(Color.WHITE.isOpposite(Color.NONE)).isFalse();
        });
    }

    @Test
    @DisplayName("블랙이면 화이트, 화이트면 블랙을 반환한다.")
    void oppositeColor_True() {
        assertAll(() -> {
            assertThat(Color.BLACK.oppositeColor()).isEqualTo(Color.WHITE);
            assertThat(Color.WHITE.oppositeColor()).isEqualTo(Color.BLACK);
            assertThat(Color.NONE.oppositeColor()).isEqualTo(Color.NONE);
        });
    }
}
