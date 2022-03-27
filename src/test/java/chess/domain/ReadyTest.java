package chess.domain;

import chess.domain.status.End;
import chess.domain.status.Ready;
import chess.domain.status.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {

    @DisplayName("레디 상태에서 게임 시작되면 White 상태로 바뀌는지 테스트")
    @Test
    void start() {
        Ready ready = new Ready();

        assertThat(ready.start()).isInstanceOf(White.class);
    }

    @DisplayName("레디 상태에서 게임 종료되면 End 상태로 바뀌는지 테스트")
    @Test
    void end() {
        Ready ready = new Ready();

        assertThat(ready.end()).isInstanceOf(End.class);
    }
}

