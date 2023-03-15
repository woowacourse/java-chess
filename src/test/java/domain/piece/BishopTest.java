package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bishop은 ")
class BishopTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Bishop bishop = new Bishop(Color.BLACK);

        // when
        String name = bishop.getName();

        // then
        assertThat(name).isEqualTo("B");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        String name = bishop.getName();

        // then
        assertThat(name).isEqualTo("b");
    }
}
