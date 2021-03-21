package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FirstCommandTest {
    @DisplayName("입력한 명령어중 첫번재 명령어가 start이다.")
    @Test
    void 첫번째_시작_명령어() {
        FirstCommand start = FirstCommand.findFirstCommand("start");

        assertThat(start).isEqualTo(FirstCommand.START);
    }

    @DisplayName("입력한 명령어중 첫번재 명령어가 end이다.")
    @Test
    void 첫번째_끝_명령어() {
        FirstCommand end = FirstCommand.findFirstCommand("end");

        assertThat(end).isEqualTo(FirstCommand.END);
    }

    @DisplayName("입력한 명령어중 첫번재 명령어가 status이다.")
    @Test
    void 첫번째_상태_명령어() {
        FirstCommand status = FirstCommand.findFirstCommand("status");

        assertThat(status).isEqualTo(FirstCommand.STATUS);
    }


    @DisplayName("입력한 명령어중 첫번재 명령어가 move이다.")
    @Test
    void 첫번째_움직임_명령어() {
        FirstCommand move = FirstCommand.findFirstCommand("move");

        assertThat(move).isEqualTo(FirstCommand.MOVE);
    }

    @DisplayName("첫번째 명령어를 잘못 입력했을 경우 예외가 발생한다.")
    @Test
    void 첫번째_명령어_예외() {
        assertThatThrownBy(() -> FirstCommand.findFirstCommand("으악"))
        .isInstanceOf(IllegalArgumentException.class);
    }
}
