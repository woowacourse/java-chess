package chess.piece;

import static chess.Fixtures.A2;
import static chess.Fixtures.A3;
import static chess.Fixtures.A5;
import static chess.Fixtures.generalBoard;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("이미_움직인_폰이_앞으로_두칸을_이동하면_예외를_발생한다")
    @Test
    void move1() {
        //when
        generalBoard.move(A2, A3);

        //then
        assertThatThrownBy(() -> generalBoard.move(A3, A5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 움직이는 폰만 앞으로 두 칸을 이동할 수 있습니다.");
    }
}
