package chess.domain.state;

import chess.domain.Board;
import chess.domain.BoardFixture;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.White;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReadyTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = BoardFixture.setup();
    }

    @DisplayName("레디 상태에서 게임 시작되면 White 상태로 바뀌는지 테스트")
    @Test
    void start() {
        Ready ready = new Ready(board);

        assertThat(ready.start()).isInstanceOf(White.class);
    }

    @DisplayName("레디 상태에서 게임 종료되면 End 상태로 바뀌는지 테스트")
    @Test
    void end() {
        Ready ready = new Ready(board);

        assertThat(ready.end()).isInstanceOf(End.class);
    }

    @DisplayName("레디 상태에서 status 실행시 에러 테스트")
    @Test
    void status() {
        Ready ready = new Ready(board);
        assertThatThrownBy(ready::status).isInstanceOf(IllegalArgumentException.class);
    }
}

