package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class MenuTest {
    @DisplayName("사용자 입력에 따라 메뉴가 선택된다.")
    @MethodSource("inputArguments")
    @ParameterizedTest
    void menu(final String input, final Menu expected) {
        // when
        final Menu menu = Menu.fromInput(input);
        //then
        Assertions.assertThat(menu).isEqualTo(expected);
    }

    static Stream<Arguments> inputArguments() {
        return Stream.of(
                Arguments.of("start", Menu.START),
                Arguments.of("end", Menu.END),
                Arguments.of("move", Menu.MOVE)
        );
    }

    @DisplayName("잘못된 커멘드에는 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"start ", " end", "mova"})
    void illegalMenu(final String input) {
        //when & then
        Assertions.assertThatCode(() -> Menu.fromInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
