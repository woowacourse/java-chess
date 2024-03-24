package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SideTest {
    @ParameterizedTest
    @CsvSource(value = {"WHITE,true", "BLACK,false", "EMPTY,false"})
    @DisplayName("흰색 진영인지 판단한다.")
    void isWhite(Side given, boolean expected) {
        //when
        boolean result = given.isWhite();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK,true", "WHITE,false", "EMPTY,false"})
    @DisplayName("흰색 진영인지 판단한다.")
    void isBlack(Side given, boolean expected) {
        //when
        boolean result = given.isBlack();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"EMPTY,true", "WHITE,false", "BLACK,false"})
    @DisplayName("흰색 진영인지 판단한다.")
    void isEmpty(Side given, boolean expected) {
        //when
        boolean result = given.isEmpty();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "WHITE,WHITE,false", "WHITE,BLACK,true", "WHITE,EMPTY,false",
            "BLACK,WHITE,true", "BLACK,BLACK,false", "BLACK,EMPTY,false",
            "EMPTY,WHITE,false", "EMPTY,BLACK,false", "EMPTY,EMPTY,false",
    })
    @DisplayName("적 기물인지 판단한다.")
    void isEnemy(Side now, Side other, boolean expected) {
        //when
        boolean result = now.isEnemy(other);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
