package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("King은 ")
class KingTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        King king = new King(Color.BLACK);

        // when
        String name = king.getName();

        // then
        assertThat(name).isEqualTo("K");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        King king = new King(Color.WHITE);

        // when
        String name = king.getName();

        // then
        assertThat(name).isEqualTo("k");
    }

}
