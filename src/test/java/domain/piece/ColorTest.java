package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @Test
    @DisplayName("reverse를 호출하면 반대 색깔이 된다")
    void colorReverseTest() {
        //given
        final Color black = Color.BLACK;

        //when
        final Color reverse = black.reverse();

        //then
        assertThat(reverse).isEqualTo(Color.WHITE);
    }

}
