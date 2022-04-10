package chess.domain.board.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class BlackPawnTest {

    private final Piece blackPawn = new Pawn(Color.BLACK);

    @Nested
    class BlackPawnMoveTest {

        @Test
        void 흑색_폰은_한칸_아래로_전진_가능() {
            Position from = Position.of("a6");
            Position to = Position.of("a5");

            boolean actual = blackPawn.canMove(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 흑색_폰은_초기화된_위치에서는_두칸_아래로_전진_가능() {
            Position from = Position.of("a7");
            Position to = Position.of("a5");

            boolean actual = blackPawn.canMove(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 흑색_폰은_초기화된_위치가_아니면_두칸_아래로_전진불가() {
            Position from = Position.of("a6");
            Position to = Position.of("a4");

            boolean actual = blackPawn.canMove(from, to);

            assertThat(actual).isFalse();
        }

        @Test
        void 흑색_폰은_위로_후진불가() {
            Position from = Position.of("a5");
            Position to = Position.of("a6");

            boolean actual = blackPawn.canMove(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class BlackAttackableRouteTest {

        @Test
        void 흑색_폰은_한칸_아래쪽_왼쪽_대각선으로_공격_가능() {
            Position from = Position.of("b6");
            Position to = Position.of("a5");

            boolean actual = blackPawn.isAttackableRoute(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 흑색_폰은_한칸_아래쪽_오른쪽_대각선으로_공격_가능() {
            Position from = Position.of("b6");
            Position to = Position.of("c5");

            boolean actual = blackPawn.isAttackableRoute(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 흑색_폰은_공격방향이_맞아도_두칸_이상_거리는_공격_불가() {
            Position from = Position.of("b6");
            Position to = Position.of("d4");

            boolean actual = blackPawn.isAttackableRoute(from, to);

            assertThat(actual).isFalse();
        }

        @Test
        void 흑색_폰은_이동가능한_곳으로_공격_불가() {
            Position from = Position.of("b6");
            Position to = Position.of("b5");

            boolean actual = blackPawn.isAttackableRoute(from, to);

            assertThat(actual).isFalse();
        }
    }
}
