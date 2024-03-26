package domain.command;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTypeTest {

    @DisplayName("commnad를 찾을 수 없으면 오류를 발생한다.")
    @Test
    void parseCommand() {
        //given
        List<String> arguments = List.of("rush", "sun", "JJang");
        //when

        //then
        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("잘못된 move command에 대해 오류를 발생한다.")
    @Test
    void moveCommand() {
        //given
        List<String> arguments = List.of("move", "rush", "sun");
        //when

        //then
        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
