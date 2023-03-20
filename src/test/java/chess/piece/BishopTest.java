package chess.piece;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.BISHOP_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Nested
    class 비숍이_움직일_때_이동방향은_ {
        @Test
        void 대각선이다() {
            //when & then
            assertThat(BISHOP_WHITE.isMovable(A1, H8)).isTrue();
        }

        @Test
        void 대각선이_아니면_False() {
            //when & then
            assertThat(BISHOP_WHITE.isMovable(A1, B8)).isFalse();
        }
    }
}
