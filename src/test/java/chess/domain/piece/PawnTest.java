package chess.domain.piece;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.PAWN_BLACK;
import static chess.fixture.PieceFixture.PAWN_WHITE;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.B5;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.B7;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C4;
import static chess.fixture.PositionFixture.C7;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Nested
    class 흰색_폰이_움직일_때_이동방향은_ {
        @Test
        void 위로_한_칸_이동한다() {
            //when & then
            assertThat(PAWN_WHITE.isMovable(B2, B3)).isTrue();
        }

        @Test
        void 첫_위치면_두칸_위로갈_수_있다() {
            //when & then
            assertThat(PAWN_WHITE.isMovable(B2, B4)).isTrue();
        }

        @Test
        void 상대_말이_있을_때만_위_대각선으로_한칸_이동할_수_있다() {
            //when & then
            assertThat(PAWN_WHITE.isMovable(B2, C3)).isTrue();
        }

        @Test
        void 올바른_방향이_아니면_False() {
            //when & then
            assertThat(PAWN_WHITE.isMovable(B2, C4)).isFalse();
        }
    }

    @Nested
    class 검은색_폰이_움직일_때_이동방향은_ {
        @Test
        void 아래로_한_칸_이동한다() {
            //when & then
            assertThat(PAWN_BLACK.isMovable(B5, B4)).isTrue();
        }

        @Test
        void 첫_위치면_두칸_아래로_갈_수_있다() {
            //when & then
            assertThat(PAWN_BLACK.isMovable(B7, B5)).isTrue();
        }

        @Test
        void 상대_말이_있을_때만_아래_대각선으로_한칸_이동할_수_있다() {
            //when & then
            assertThat(PAWN_BLACK.isMovable(C7, B6)).isTrue();
        }

        @Test
        void 올바른_방향이_아니면_False() {
            //when & then
            assertThat(PAWN_BLACK.isMovable(B2, C4)).isFalse();
        }
    }
}
