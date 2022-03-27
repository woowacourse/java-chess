package chess.domain;

import chess.domain.state.Black;
import chess.domain.state.White;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.BLACK_SOURCE;
import static chess.domain.PositionFixture.BLACK_TARGET;
import static org.assertj.core.api.Assertions.assertThat;

public class BlackTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = BoardFixture.setup();
    }

    @DisplayName("Black 상태에서 턴이 바뀌면 White 상태로 되는 지 테스트")
    @Test
    void changeTurn() {
        Black black = new Black(board);

        assertThat(black.changeTurn(BLACK_SOURCE, BLACK_TARGET)).isInstanceOf(White.class);
    }
}
