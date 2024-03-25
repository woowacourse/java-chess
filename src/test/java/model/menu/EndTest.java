package model.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("End상태일 때 어떤 동작도 하지 않는다.")
    @Test
    void play() {
        final Menu end = new End();
        final List<String> input = List.of("start");
        final ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> end.play(input, chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 종료되었습니다");
    }

    @DisplayName("상태가 End일 때는 게임 진행 중이 아니다.")
    @Test
    void isRunning() {
        final Menu end = new End();

        assertThat(end.isRunning()).isFalse();
    }
}
