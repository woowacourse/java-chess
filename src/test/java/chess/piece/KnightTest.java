package chess.piece;

import chess.board.Position;
import chess.fixture.FixturePosition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Nested
    class 나이트가_움직일_때_이동방향은_ {
        @Test
        void 두_칸_전진한_상태에서_좌우로_한_칸_움직일_수_있다() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.C2;

            //when & then
            assertThat(knight.isMovable(from, to)).isTrue();
        }

        @Test
        void 대각선이_아니면_False() {
            //given
            Knight knight = new Knight(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B8;

            //when & then
            assertThat(knight.isMovable(from, to)).isFalse();
        }
    }
}
