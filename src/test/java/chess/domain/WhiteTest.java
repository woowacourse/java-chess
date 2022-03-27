package chess.domain;

import chess.domain.state.Black;
import chess.domain.state.End;
import chess.domain.state.White;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhiteTest {

    @DisplayName("White 상태에서 턴이 바뀌면 Black 상태로 되는 지 테스트")
    @Test
    void changeTurn() {
        White white = new White();

        assertThat(white.changeTurn()).isInstanceOf(Black.class);
    }

    @DisplayName("White 상태에서 게임이 종료되면 end 상태로 되는 지 테스트")
    @Test
    void end() {
        White white = new White();

        assertThat(white.end()).isInstanceOf(End.class);
    }
}
