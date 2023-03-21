package chess.domain.piece;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.QUEEN_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Nested
    class 퀸이_움직일_때_이동방향은_ {
        @Test
        void 대각선이다() {
            //when & then
            assertThat(QUEEN_WHITE.isMovable(A1, H8)).isTrue();
        }

        @Test
        void 동일한_Rank_혹은_File이다() {
            //when & then
            assertThat(QUEEN_WHITE.isMovable(A1, A8)).isTrue();
        }

        @Test
        void 동일한_Rank_혹은_File이_아니면_False() {
            //when & then
            assertThat(QUEEN_WHITE.isMovable(A1, B8)).isFalse();
        }

        @Test
        void 대각선이_아니면_False() {
            //when & then
            assertThat(QUEEN_WHITE.isMovable(A1, B8)).isFalse();
        }
    }
}
