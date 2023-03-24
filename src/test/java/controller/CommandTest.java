package controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.game.Command;

class CommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"adf", "adsfa", "azxcvzc"})
    @DisplayName("올바르지 않은 명령어 입력시 예외가 발생한다.")
    void findRunCommand(String input) {
        assertThatThrownBy(() -> Command.find(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령입니다.");
    }
}
