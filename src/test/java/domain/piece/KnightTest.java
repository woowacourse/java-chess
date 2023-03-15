package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Knight는 ")
class KnightTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Knight knight = new Knight(Color.BLACK);

        // when
        String name = knight.getName();

        // then
        assertThat(name).isEqualTo("N");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        String name = knight.getName();

        // then
        assertThat(name).isEqualTo("n");
    }

}
