package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Queen은 ")
class QueenTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Queen queen = new Queen(Color.BLACK);

        // when
        String name = queen.getName();

        // then
        assertThat(name).isEqualTo("Q");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        String name = queen.getName();

        // then
        assertThat(name).isEqualTo("q");
    }

}
