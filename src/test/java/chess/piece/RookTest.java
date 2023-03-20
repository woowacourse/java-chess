package chess.piece;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.ROOK_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B8;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Nested
    class 룩이_움직일_때_이동방향은_ {
        @Test
        void 동일한_Rank_혹은_File이다() {
            //when & then
            assertThat(ROOK_WHITE.isMovable(A1, A8)).isTrue();
        }

        @Test
        void 동일한_Rank_혹은_File이_아니면_False() {
            //when & then
            assertThat(ROOK_WHITE.isMovable(A1, B8)).isFalse();
        }
    }
}
