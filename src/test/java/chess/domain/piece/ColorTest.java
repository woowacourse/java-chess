package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("색상 테스트")
class ColorTest {

    @Test
    @DisplayName("흰색이 검정색으로 바뀌는지 테스트")
    void whiteNext() {
        Color color = Color.WHITE;

        assertThat(color.next()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("검정색이 흰색으로 바뀌는지 테스트")
    void blackNext() {
        Color color = Color.BLACK;

        assertThat(color.next()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검정색인지 흰색인지 확인하는 테스트 - Black")
    void blackColorTest() {
        Color color = Color.BLACK;

        assertThat(color.isBlack()).isTrue();

        assertThat(color.isWhite()).isFalse();

        assertThat(color.color()).isEqualTo("BLACK");
    }

    @Test
    @DisplayName("흰색인지 검정색인지 확인하는 테스트 - White")
    void whiteColorTest() {
        Color color = Color.WHITE;

        assertThat(color.isBlack()).isFalse();

        assertThat(color.isWhite()).isTrue();

        assertThat(color.color()).isEqualTo("WHITE");
    }
}