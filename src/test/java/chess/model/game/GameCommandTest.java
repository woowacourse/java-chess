package chess.model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameCommandTest {

    @ParameterizedTest(name = "입력한 커맨드가 {0}이라면 GameCommand.{1}을 반환한다")
    @DisplayName("findGameCommand() 성공 테스트")
    @MethodSource("provideFindGameCommandArguments")
    void findGameCommand_givenValidCommand_thenReturnCommand(final String command, final GameCommand expected) {
        // when
        final GameCommand gameCommand = GameCommand.findGameCommand(command);

        // then
        assertThat(gameCommand).isSameAs(expected);
    }

    private static Stream<Arguments> provideFindGameCommandArguments() {
        return Stream.of(
                Arguments.of("start", GameCommand.START), Arguments.of("end", GameCommand.END),
                Arguments.of("move", GameCommand.MOVE), Arguments.of("status", GameCommand.STATUS)
        );
    }

    @Test
    @DisplayName("입력한 커맨드가 유효하지 않은 커맨드라면 예외가 발생한다.")
    void findGameCommand_givenInvalidCommand_thenFail() {
        // when, then
        assertThatThrownBy(() -> GameCommand.findGameCommand("z"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어입니다.");
    }

    @ParameterizedTest(name = "GameCommand.{0}은 {1}을 반환한다.")
    @DisplayName("isStart() 테스트")
    @MethodSource("provideIsStartArguments")
    void isStart_whenCall_thenReturnIsStart(final GameCommand gameCommand, final boolean expected) {
        // when
        final boolean actual = gameCommand.isStart();

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsStartArguments() {
        return Stream.of(
                Arguments.of(GameCommand.START, true), Arguments.of(GameCommand.MOVE, false),
                Arguments.of(GameCommand.END, false), Arguments.of(GameCommand.STATUS, false)
        );
    }

    @ParameterizedTest(name = "GameCommand.{0}은 {1}을 반환한다.")
    @DisplayName("isMove() 테스트")
    @MethodSource("provideIsMoveArguments")
    void isMove_whenCall_thenReturnIsStart(final GameCommand gameCommand, final boolean expected) {
        // when
        final boolean actual = gameCommand.isMove();

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsMoveArguments() {
        return Stream.of(
                Arguments.of(GameCommand.START, false), Arguments.of(GameCommand.MOVE, true),
                Arguments.of(GameCommand.END, false), Arguments.of(GameCommand.STATUS, false)
        );
    }

    @ParameterizedTest(name = "GameCommand.{0}은 {1}을 반환한다.")
    @DisplayName("isEnd() 테스트")
    @MethodSource("provideIsEndArguments")
    void isEnd_whenCall_thenReturnIsEnd(final GameCommand gameCommand, final boolean expected) {
        // when
        final boolean actual = gameCommand.isEnd();

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsEndArguments() {
        return Stream.of(
                Arguments.of(GameCommand.START, false), Arguments.of(GameCommand.MOVE, false),
                Arguments.of(GameCommand.END, true), Arguments.of(GameCommand.STATUS, false)
        );
    }

    @ParameterizedTest(name = "GameCommand.{0}은 {1}을 반환한다.")
    @DisplayName("isStatus() 테스트")
    @MethodSource("provideIsStatusArguments")
    void isStatus_whenCall_thenReturnIsStatus(final GameCommand gameCommand, final boolean expected) {
        // when
        final boolean actual = gameCommand.isStatus();

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsStatusArguments() {
        return Stream.of(
                Arguments.of(GameCommand.START, false), Arguments.of(GameCommand.MOVE, false),
                Arguments.of(GameCommand.END, false), Arguments.of(GameCommand.STATUS, true)
        );
    }
}
