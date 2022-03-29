package chess.domain.state;

import chess.domain.Board;
import chess.domain.BoardFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.WHITE_SOURCE;
import static chess.domain.PositionFixture.WHITE_TARGET;
import static org.assertj.core.api.Assertions.assertThat;

public class WhiteTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = BoardFixture.setup();
    }

    @DisplayName("White 상태에서 턴이 바뀌면 Black 상태로 되는 지 테스트")
    @Test
    void changeTurn() {
        White white = new White(board);

        assertThat(white.changeTurn(WHITE_SOURCE, WHITE_TARGET)).isInstanceOf(Black.class);
    }

    @DisplayName("White 상태에서 게임이 종료되면 end 상태로 되는 지 테스트")
    @Test
    void end() {
        White white = new White(board);

        assertThat(white.end()).isInstanceOf(End.class);
    }
}
