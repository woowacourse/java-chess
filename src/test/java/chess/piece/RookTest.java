package chess.piece;

import chess.board.Position;
import chess.fixture.FixturePosition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Nested
    class 룩이_움직일_때_이동방향은_ {
        @Test
        void 동일한_Rank_혹은_File이다() {
            //given
            Rook rook = new Rook(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.A8;

            //when & then
            assertThat(rook.isMovable(from, to)).isTrue();
        }

        @Test
        void 동일한_Rank_혹은_File이_아니면_False() {
            //given
            Rook rook = new Rook(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B8;

            //when & then
            assertThat(rook.isMovable(from, to)).isFalse();
        }
    }
}
