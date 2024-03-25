package model.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @DisplayName("게임이 진행중일 때 end를 입력하면 End를 반환한다")
    @Test
    void playEnd() {
        final Menu running = new Running();
        final List<String> input = List.of("end");
        final ChessGame chessGame = new ChessGame();

        assertThat(running.play(input, chessGame)).isInstanceOf(End.class);
    }

    @DisplayName("게임이 진행중일 때 Move명령어를 입력하면 Running을 반환한다")
    @Test
    void playMove() {
        final Menu running = new Running();
        final List<String> input = List.of("move", "a2", "a4");
        final ChessGame chessGame = new ChessGame();

        assertThat(running.play(input, chessGame)).isInstanceOf(Running.class);
    }

    @DisplayName("게임이 진행중일 때 start를 입력하면 예외를 발생시킨다.")
    @Test
    void play() {
        final Menu running = new Running();
        final List<String> input = List.of("start");
        final ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> running.play(input, chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 게임이 진행중입니다.");
    }

    @DisplayName("상태가 Running일 때는 게임이 진행 중이다.")
    @Test
    void isRunning() {
        final Menu running = new Running();

        assertThat(running.isRunning()).isTrue();
    }
}
