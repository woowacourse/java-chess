package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("명령어")
class CommandTest {

    @Test
    @DisplayName("생성자를 통해 생성한다.")
    void createWithConstructorTest() {
        // given
        CommandType type = CommandType.MOVE;
        List<String> arguments = List.of("move", "b2", "b3");
        Command command = new Command(type, arguments);

        // when & then
        assertThat(command.arguments()).isEqualTo(arguments);
    }

    @Test
    @DisplayName("move 명령어의 인자가 2개 미만일 경우 예외가 발생한다.")
    void validateMoveArgumentCountTest() {
        // given
        List<String> arguments = List.of("move", "b2");

        // when & then
        assertThatCode(() -> Command.from(arguments))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("move 명령어는 인자가 2개 필요합니다.");
    }

    @Test
    @DisplayName("타입이 같은지 확인한다.")
    void isTypeTest() {
        // given
        Command command = Command.from(List.of("start"));

        // when
        boolean type = command.isType(CommandType.START);

        // then
        assertThat(type).isTrue();
    }

    @Test
    @DisplayName("인덱스를 통해 인자를 가져온다.")
    void getArgumentTest() {
        // given
        CommandType type = CommandType.MOVE;
        List<String> arguments = List.of("move", "b2", "b3");
        Command command = new Command(type, arguments);

        // when & then
        assertThat(command.getArgument(2)).isEqualTo("b3");
    }
}
