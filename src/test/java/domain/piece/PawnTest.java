package domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pawn은 ")
class PawnTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("P");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("p");
    }

}
