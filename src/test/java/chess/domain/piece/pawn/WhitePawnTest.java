package chess.domain.piece.pawn;

import static chess.fixture.StrategyFixture.CLEAR_PATH_STRATEGY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class WhitePawnTest {

    private Pawn pawn;

    @BeforeEach
    void setUp() {
        pawn = new WhitePawn(Position.of("b2"));
    }

    @Test
    void 백색_폰은_두번째_rank에_생성() {
        Pawn actual = new WhitePawn(0);
        Pawn expected = new WhitePawn(Position.of("a2"));

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class ForwardOrJumpTest {

        @Test
        void 백색_폰은_한칸_위로_전진_가능() {
            pawn.move(Position.of("b3"), CLEAR_PATH_STRATEGY);

            Pawn expected = new WhitePawn(Position.of("b3"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 백색_폰이_아래로_후진시_예외발생() {
            Pawn pawn = new WhitePawn(Position.of("a4"));

            assertThatCode(() -> pawn.move(Position.of("a3"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @Test
        void 백색_폰은_초기화된_위치에서는_두칸_위로_전진_가능() {
            pawn.move(Position.of("b4"), CLEAR_PATH_STRATEGY);

            Pawn expected = new WhitePawn(Position.of("b4"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 백색_폰은_초기화된_위치가_아니면_두칸_위로_전진_시도시_예외발생() {
            Pawn pawn = new WhitePawn(Position.of("a3"));

            assertThatCode(() -> pawn.move(Position.of("a5"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    class AttackTest {

        @Test
        void 백색_폰은_한칸_위쪽_왼쪽_대각선으로_공격_가능() {
            pawn.attack(Position.of("a3"), CLEAR_PATH_STRATEGY);

            Pawn expected = new WhitePawn(Position.of("a3"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 백색_폰은_한칸_위쪽_오른쪽_대각선으로_공격_가능() {
            pawn.attack(Position.of("c3"), CLEAR_PATH_STRATEGY);

            Pawn expected = new WhitePawn(Position.of("c3"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 백색_폰은_한칸_위쪽의_대각선이_아닌_위치로_공격_시도시_예외발생() {
            assertThatCode(() -> pawn.attack(Position.of("b3"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("공격할 수 없는 위치입니다.");
        }
    }
}
