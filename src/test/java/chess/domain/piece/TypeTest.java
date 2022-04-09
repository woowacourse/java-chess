package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypeTest {

    @ParameterizedTest
    @CsvSource(value = {"N:Knight", "K:King"}, delimiter = ':')
    @DisplayName("이름으로 기물을 찾는다.")
    void from(String name, Type expected) {
        //when
        Type actual = Type.from(name);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("존재하지 않는 기물의 이름인 경우 예외를 발생시킨다.")
    void from_exception() {
        //given
        String pieceName = "TEST";
        //then
        assertThatThrownBy(() -> Type.from(pieceName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 기물 종류입니다.");
    }
}