package chess.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters"})
class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start, START", "move, MOVE", "end, END"})
    void 입력된_문자열에_해당하는_명령어가_있다면_반환한다(String input, Command expected) {
        assertThat(Command.findCommandByString(input))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"go", "START"})
    void 입력된_문자열에_해당하는_명령어가_없으면_예외(String input) {
        assertThatThrownBy(()-> Command.findCommandByString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("일치하는 명령어가 없습니다");
    }
}