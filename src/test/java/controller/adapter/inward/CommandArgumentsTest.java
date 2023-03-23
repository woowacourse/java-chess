package controller.adapter.inward;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CommandArgumentsTest {

    @Test
    @DisplayName("명령 인자가 0개일 때, 가져오려고 하면 예외를 발생시킨다")
    void getArgumentOfWhenZero() {
        CommandArguments commandArguments = CommandArguments.of(
                Collections.emptyList()
        );

        assertThatThrownBy(() -> commandArguments.getArgumentOf(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("명령 인자가 여러 개일 때, 해당 위치의 명령 인자를 가져올 수 있다")
    void getArgumentOfWhenProper() {
        CommandArguments commandArguments = CommandArguments.of(
                List.of("b2", "b3")
        );

        assertThat(commandArguments.getArgumentOf(0))
                .isEqualTo("b2");
    }

    @Test
    @DisplayName("명령 인자가 여러 개일 때, 개수 이상으로 가져오려고 하면 예외를 발생시킨다")
    void getArgumentOfWhenSeveral() {
        CommandArguments commandArguments = CommandArguments.of(
                List.of("b2", "b3")
        );

        assertThatThrownBy(() -> commandArguments.getArgumentOf(2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
