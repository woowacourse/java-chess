package chess.domain.board.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WhitePawnTest {

    public final Piece whitePawn = new Pawn(Color.WHITE);

    @Nested
    class WhitePawnMoveTest {

        @Test
        void 백색_폰은_한칸_위로_전진_가능() {
            Position from = Position.of("a3");
            Position to = Position.of("a4");

            boolean actual = whitePawn.canMove(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 백색_폰은_초기화된_위치에서는_두칸_위로_전진_가능() {
            Position from = Position.of("a2");
            Position to = Position.of("a4");

            boolean actual = whitePawn.canMove(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 백색_폰은_초기화된_위치가_아니면_두칸_위로_전진불가() {
            Position from = Position.of("a3");
            Position to = Position.of("a5");

            boolean actual = whitePawn.canMove(from, to);

            assertThat(actual).isFalse();
        }

        @Test
        void 백색_폰은_아래로_후진불가() {
            Position from = Position.of("a5");
            Position to = Position.of("a4");

            boolean actual = whitePawn.canMove(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class WhiteAttackableRouteTest {

        @Test
        void 백색_폰은_한칸_위쪽_왼쪽_대각선으로_공격_가능() {
            Position from = Position.of("b2");
            Position to = Position.of("a3");

            boolean actual = whitePawn.isAttackableRoute(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 백색_폰은_한칸_아래쪽_오른쪽_대각선으로_공격_가능() {
            Position from = Position.of("b2");
            Position to = Position.of("c3");

            boolean actual = whitePawn.isAttackableRoute(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 백색_폰은_공격방향이_맞아도_두칸_이상_거리는_공격_불가() {
            Position from = Position.of("b2");
            Position to = Position.of("d4");

            boolean actual = whitePawn.isAttackableRoute(from, to);

            assertThat(actual).isFalse();
        }

        @Test
        void 흑색_폰은_이동가능한_곳으로_공격_불가() {
            Position from = Position.of("b2");
            Position to = Position.of("b3");

            boolean actual = whitePawn.isAttackableRoute(from, to);

            assertThat(actual).isFalse();
        }
    }
}