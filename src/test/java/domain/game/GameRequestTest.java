package domain.game;

import static domain.Fixture.Positions.B3;
import static domain.Fixture.Positions.E1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import domain.GameCommand;
import domain.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameRequestTest {
    @Test
    @DisplayName("인자 없이 타입만 가지는 명령을 생성할 수 있다.")
    void noArgumentTest() {
        assertThatCode(() -> GameRequest.ofNoArgument(GameCommand.START))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("MOVE 타입의 명령인 경우 두 개의 Position 인자를 가질 수 있다.")
    void moveRequestArgumentTest() {
        List<Position> positions = List.of(E1, B3);
        GameRequest gameRequest = new GameRequest(GameCommand.MOVE, positions);

        assertAll(
                () -> assertThat(gameRequest.source()).isEqualTo(E1),
                () -> assertThat(gameRequest.destination()).isEqualTo(B3)
        );
    }

    @ParameterizedTest
    @MethodSource("nonMoveCommandCase")
    @DisplayName("MOVE 타입의 명령이 아닌 경우 인자를 가져오면 예외가 발생한다.")
    void nonMoveRequestArgumentTest(GameCommand gameCommand) {
        List<Position> positions = List.of(E1, B3);
        GameRequest gameRequest = new GameRequest(gameCommand, positions);

        assertAll(
                () -> assertThatThrownBy(gameRequest::source)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("유효하지 않은 커멘드 타입입니다."),
                () -> assertThatThrownBy(gameRequest::destination)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("유효하지 않은 커멘드 타입입니다.")
        );
    }

    static Stream<Arguments> nonMoveCommandCase() {
        return Stream.of(
                Arguments.of(GameCommand.START),
                Arguments.of(GameCommand.STATUS),
                Arguments.of(GameCommand.END)
        );
    }

    @ParameterizedTest
    @MethodSource("allCommandCase")
    @DisplayName("GameCommand 의 각 속성 값을 포워딩한다.")
    void methodForwardTest(GameCommand gameCommand) {
        GameRequest gameRequest = GameRequest.ofNoArgument(gameCommand);

        assertAll(
                () -> assertThat(gameRequest.isContinuable()).isEqualTo(gameCommand.isContinuable()),
                () -> assertThat(gameRequest.isStart()).isEqualTo(gameCommand.isStart()),
                () -> assertThat(gameRequest.isStatus()).isEqualTo(gameCommand.isStatus()),
                () -> assertThat(gameRequest.isEnd()).isEqualTo(gameCommand.isEnd())
        );
    }

    static Stream<Arguments> allCommandCase() {
        return Stream.of(
                Arguments.of(GameCommand.START),
                Arguments.of(GameCommand.MOVE),
                Arguments.of(GameCommand.STATUS),
                Arguments.of(GameCommand.END)
        );
    }
}
