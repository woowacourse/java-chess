package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommandTest {

    @ParameterizedTest
    @DisplayName("생성 확인")
    @MethodSource("createCommand")
    void create(List<String> input, List<String> expected) {
        assertThat(Command.from(input).getFlags()).isEqualTo(expected);
    }

    static Stream<Arguments> createCommand() {
        return Stream.of(
                Arguments.of(Collections.singletonList("start"), Collections.emptyList()),
                Arguments.of(Collections.singletonList("END"), Collections.emptyList()),
                Arguments.of(Arrays.asList("move", "b1", "b2"), Arrays.asList("b1", "b2")),
                Arguments.of(Collections.singletonList("status"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("move의 인자 수가 2개가 아닐 경우 검증")
    @MethodSource("createInvalidMoveSize")
    void validateMoveSize(List<String> input) {
        assertThatThrownBy(() -> {
            Command.from(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 커맨드의 인자는 2개여야 합니다.");
    }

    static List<List<String>> createInvalidMoveSize() {
        return Arrays.asList(
                Arrays.asList("move", "b1"),
                Arrays.asList("move"),
                Arrays.asList("move", "b1", "b2", "b3")
        );
    }

    @ParameterizedTest
    @DisplayName("Command가 START인지 확인")
    @MethodSource("createBoolean")
    void isStart(List<String> input, boolean expected) {
        assertThat(Command.from(input).isStart()).isEqualTo(expected);
    }

    static Stream<Arguments> createBoolean() {
        return Stream.of(
                Arguments.of(Collections.singletonList("start"), true),
                Arguments.of(Collections.singletonList("end"), false),
                Arguments.of(Arrays.asList("move", "b1", "b3"), false),
                Arguments.of(Collections.singletonList("status"), false)
        );
    }

    @ParameterizedTest
    @DisplayName("Command가 End가 아닌지 확인")
    @MethodSource("createEnd")
    void isNotEnd(List<String> input, boolean expected) {
        assertThat(Command.from(input).isNotEnd()).isEqualTo(expected);
    }

    static Stream<Arguments> createEnd() {
        return Stream.of(
                Arguments.of(Collections.singletonList("start"), true),
                Arguments.of(Collections.singletonList("end"), false),
                Arguments.of(Arrays.asList("move", "b1", "b3"), true),
                Arguments.of(Collections.singletonList("status"), true)
        );
    }

    @ParameterizedTest
    @DisplayName("Command가 Move인지 확인")
    @MethodSource("createMove")
    void isMove(List<String> input, boolean expected) {
        assertThat(Command.from(input).isMove()).isEqualTo(expected);
    }

    static Stream<Arguments> createMove() {
        return Stream.of(
                Arguments.of(Collections.singletonList("start"), false),
                Arguments.of(Collections.singletonList("end"), false),
                Arguments.of(Arrays.asList("move", "b1", "b3"), true),
                Arguments.of(Collections.singletonList("status"), false)
        );
    }

    @ParameterizedTest
    @DisplayName("Command가 Status인지 확인")
    @MethodSource("createStatus")
    void isStatus(List<String> input, boolean expected) {
        assertThat(Command.from(input).isStatus()).isEqualTo(expected);
    }

    static Stream<Arguments> createStatus() {
        return Stream.of(
                Arguments.of(Collections.singletonList("start"), false),
                Arguments.of(Collections.singletonList("end"), false),
                Arguments.of(Arrays.asList("move", "b1", "b3"), false),
                Arguments.of(Collections.singletonList("status"), true)
        );
    }

    @Test
    @DisplayName("source position 확인")
    void getSource() {
        List<String> input = Arrays.asList("move", "b1", "b2");
        Command command = Command.from(input);

        assertThat(command.getSource()).isEqualTo("b1");
    }

    @Test
    @DisplayName("target position 확인")
    void getTarget() {
        List<String> input = Arrays.asList("move", "b1", "b2");
        Command command = Command.from(input);

        assertThat(command.getTarget()).isEqualTo("b2");
    }

    @Test
    void moveWithAnotherCommand() {
        List<String> input = Collections.singletonList("start");
        Command command = Command.from(input);

        assertThatThrownBy(command::getSource)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("move 명령만 사용할 수 있습니다.");
    }
}