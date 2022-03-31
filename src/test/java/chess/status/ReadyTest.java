package chess.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.game.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("준비 상태에서 말을 움직일 경우 예외를 발생한다.")
    void readyCannotMove() {
        assertThatThrownBy(() -> Ready.start(Command.MOVE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("준비 상태에서 게임을 시작하면 Start 상태가 된다.")
    void startFromReady() {
        assertThat(Ready.start(Command.START)).isInstanceOf(Move.class);
    }

    @Test
    @DisplayName("준비 상태에서 게임을 끝내면 Finished 상태가 된다.")
    void finishFromReady() {
        assertThat(Ready.start(Command.END)).isInstanceOf(Finished.class);
    }

}
