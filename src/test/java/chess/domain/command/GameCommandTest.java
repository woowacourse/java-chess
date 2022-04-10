package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameCommandTest {

    @Test
    @DisplayName("게임 커맨드를 생성한다.")
    void construct() {
        assertThatCode(() ->
                GameCommand.of("start"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정해진 커맨드 외의 게임 커맨드를 입력하면 안된다.")
    void constructThrowException() {
        assertThatThrownBy(() ->
                GameCommand.of("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 커맨드가 아닙니다.");
    }

    @Test
    @DisplayName("싱글 타입 커맨드는 하나의 인자만 전달해야 합니다.")
    void constructSingleCommandThrowExceptionByArgumentLength() {
        assertThatThrownBy(() ->
                GameCommand.of("start", "a1", "a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 타입의 커맨드는 인자가 하나여야합니다.");
    }

    @Test
    @DisplayName("Move 커맨드는 인자가 3개여야합니다.")
    void constructMoveCommandThrowExceptionByArgumentLength() {
        assertThatThrownBy(() ->
                GameCommand.of("move", "a1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 타입의 커맨드는 인자가 3개여야합니다.");
    }

    @Test
    @DisplayName("Start 커맨드인지 확인한다.")
    void isStart() {
        final GameCommand gameCommand = GameCommand.of("start");
        final boolean actual = gameCommand.isSameCommandType(CommandType.START);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("End 커맨드인지 확인한다.")
    void isEnd() {
        final GameCommand gameCommand = GameCommand.of("end");
        final boolean actual = gameCommand.isSameCommandType(CommandType.END);

        assertThat(actual).isTrue();
    }


    @Test
    @DisplayName("이동할 말의 위치를 반환한다.")
    void getFromPosition() {
        final GameCommand gameCommand = GameCommand.of("move", "a1", "a2");
        final Position actual = gameCommand.getFromPosition();

        assertThat(actual).isEqualTo(Position.of("a1"));
    }

    @Test
    @DisplayName("이동할 말의 위치를 반환한다.")
    void getFromPositionThrowException() {
        final GameCommand gameCommand = GameCommand.of("start");

        assertThatThrownBy(() ->
                gameCommand.getFromPosition())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 커맨드는 이 작업을 할 수 없습니다.");
    }

    @Test
    @DisplayName("말의 이동할 위치를 반환한다.")
    void getToPosition() {
        final GameCommand gameCommand = GameCommand.of("move", "a1", "a2");
        final Position actual = gameCommand.getToPosition();

        assertThat(actual).isEqualTo(Position.of("a2"));
    }

    @Test
    @DisplayName("이동할 말의 위치를 반환한다.")
    void getToPositionThrowException() {
        final GameCommand gameCommand = GameCommand.of("end");

        assertThatThrownBy(() ->
                gameCommand.getToPosition())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 커맨드는 이 작업을 할 수 없습니다.");
    }
}
