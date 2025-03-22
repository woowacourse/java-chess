package chess.board;

import static chess.Fixtures.A1;
import static chess.Fixtures.A3;
import static chess.Fixtures.generalBoard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void move() {
        //when
        //then
        Assertions.assertThatThrownBy(() -> generalBoard.move(A1, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중간에 다른 기물을 뛰어넘을 수 없습니다.");
    }
}
