package chess.piece;

import static chess.Fixtures.A3;
import static chess.Fixtures.B1;
import static chess.Fixtures.generalBoard;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("Knight는_중간에_다른_기물을_뛰어넘을_수_있다")
    @Test
    void move1() {
        //when
        //then
        assertThatCode(() -> generalBoard.move(B1, A3))
                .doesNotThrowAnyException();
    }
}
