package chess.domain.piece;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.KNIGHT_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C2;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Nested
    class 나이트가_움직일_때_이동방향은_ {
        @Test
        void 두_칸_전진한_상태에서_좌우로_한_칸_움직일_수_있다() {
            //when & then
            assertThat(KNIGHT_WHITE.isMovable(A1, C2)).isTrue();
        }

        @Test
        void 대각선이_아니면_False() {
            //when & then
            assertThat(KNIGHT_WHITE.isMovable(A1, B8)).isFalse();
        }
    }
}
