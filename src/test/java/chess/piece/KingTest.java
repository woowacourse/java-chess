package chess.piece;

import chess.board.Position;
import chess.fixture.FixturePosition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Nested
    class 킹이_움직일_때_이동방향은_ {
        @Test
        void 상하좌우_대각선으로_한칸이다() {
            //given
            King king = new King(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B2;

            //when & then
            assertThat(king.isMovable(from, to)).isTrue();
        }

        @Test
        void 상하좌우_대각선으로_한칸이_아니면_False() {
            King king = new King(Team.WHITE);

            Position from = FixturePosition.A1;
            Position to = FixturePosition.B3;

            //when & then
            assertThat(king.isMovable(from, to)).isFalse();
        }
    }
}
