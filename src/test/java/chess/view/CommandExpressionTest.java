package chess.view;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CommandExpressionTest {

    @Test
    @DisplayName("시작 문자열을 받고, 명령어로 변환한다.")
    void startCommand() {
        CommandExpression startCommand = CommandExpression.of("start");

        assertThat(startCommand.isStart()).isTrue();
    }

    @Test
    @DisplayName("이동 문자열을 받고, 명령어로 변환한다.")
    void moveCommand() {
        CommandExpression moveCommand = CommandExpression.of("move a1 a2");

        Position sourcePosition = moveCommand.getSourcePosition();
        Position targetPosition = moveCommand.getTargetPosition();
        Assertions.assertAll(
                () -> assertThat(moveCommand.isMove()).isTrue(),
                () -> assertThat(sourcePosition).isEqualTo(Position.of(File.A, Rank.ONE)),
                () -> assertThat(targetPosition).isEqualTo(Position.of(File.A, Rank.TWO))
        );
    }

    @Test
    @DisplayName("중단 문자열을 받고, 명령어로 변환한다.")
    void endCommand() {
        CommandExpression endCommand = CommandExpression.of("end");

        assertThat(endCommand.isEnd()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("명령어에 해당하는 인자의 갯수가 아니면 예외를 발생시킨다.")
    @ValueSource(strings = {"start !", "end !", "move a1", "move a1 a2 a3"})
    void validateCommandArgumentsSizeFail(String input) {
        assertThatCode(() -> CommandExpression.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("명령어에 맞는 인자의 갯수가 아닙니다.");
    }
}
