package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rook은 ")
class RookTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Rook rook = new Rook(Color.BLACK);

        // when
        String name = rook.getName();

        // then
        assertThat(name).isEqualTo("R");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        String name = rook.getName();

        // then
        assertThat(name).isEqualTo("r");
    }
}
