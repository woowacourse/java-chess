package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.Output;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    private static ByteArrayOutputStream outputMessage;

    @BeforeEach
    void setUpStreams() {
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    void restoresStreams() {
        System.setOut(System.out);
    }

    @Test
    @DisplayName("게임이 정상적으로 start 되는지 확인")
    void start() {
        State ready = new Ready();

        assertThatCode(ready::start)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("move 시 예외 발생")
    void moveException() {
        State ready = new Ready();

        assertThatThrownBy(() -> ready.move(Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @Test
    @DisplayName("게임이 정상적으로 end 되는지 확인")
    void end() {
        State ready = new Ready();

        assertThatCode(ready::end)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 ready 상태일 때 status 시 예외 발생")
    void statusToInitialReadyException() {
        State ready = new Ready();

        assertThatThrownBy(() -> ready.status(Output::printScore))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @Test
    @DisplayName("게임이 종료된 이후의 ready 상태일 때 status 시 점수 출력")
    void status() {
        State ready = new Ready(new Board(HashMap::new));

        ready.status(Output::printScore);

        assertEquals("검은색의 점수는 0.0점 입니다.\n흰색의 점수는 0.0점 입니다.\n", outputMessage.toString());
    }

    @Test
    @DisplayName("게임이 실행중이 아닌 것을 확인")
    void isRunning() {
        State ready = new Ready();

        assertThat(ready.isRunning()).isFalse();
    }

    @Test
    @DisplayName("게임이 끝나지 않은 것을 확인")
    void isEnded() {
        State ready = new Ready();

        assertThat(ready.isEnded()).isFalse();
    }
}