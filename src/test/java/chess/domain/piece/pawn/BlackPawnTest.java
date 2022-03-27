package chess.domain.piece.pawn;

import static chess.fixture.StrategyFixture.CLEAR_PATH_STRATEGY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class BlackPawnTest {

    private Pawn pawn;

    @BeforeEach
    void setUp() {
        pawn = new BlackPawn(Position.of("b7"));
    }

    @Test
    void 흑색_폰은_7번째_rank에_생성() {
        Pawn actual = new BlackPawn(0);
        Pawn expected = new BlackPawn(Position.of("a7"));

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class ForwardOrJumpTest {

        @Test
        void 흑색_폰은_한칸_아래로_전진_가능() {
            pawn.move(Position.of("b6"), CLEAR_PATH_STRATEGY);

            Pawn expected = new BlackPawn(Position.of("b6"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 흑색_폰이_위로_후진시_예외발생() {
            Pawn pawn = new BlackPawn(Position.of("a4"));

            assertThatCode(() -> pawn.move(Position.of("a5"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @Test
        void 흑색_폰은_초기화된_위치에서는_두칸_아래로_전진_가능() {
            pawn.move(Position.of("b5"), CLEAR_PATH_STRATEGY);

            Pawn expected = new BlackPawn(Position.of("b5"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 흑색_폰은_초기화된_위치가_아니면_두칸_위로_전진_시도시_예외발생() {
            Pawn pawn = new BlackPawn(Position.of("a6"));

            assertThatCode(() -> pawn.move(Position.of("a8"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    class AttackTest {

        @Test
        void 흑색_폰은_한칸_아래쪽_왼쪽_대각선으로_공격_가능() {
            pawn.attack(Position.of("a6"), CLEAR_PATH_STRATEGY);

            Pawn expected = new BlackPawn(Position.of("a6"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 흑색_폰은_한칸_아래쪽_오른쪽_대각선으로_공격_가능() {
            pawn.attack(Position.of("c6"), CLEAR_PATH_STRATEGY);

            Pawn expected = new BlackPawn(Position.of("c6"));

            assertThat(pawn).isEqualTo(expected);
        }

        @Test
        void 흑색_폰은_한칸_아래쪽의_대각선이_아닌_위치로_공격_시도시_예외발생() {
            assertThatCode(() -> pawn.attack(Position.of("b6"), CLEAR_PATH_STRATEGY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("공격할 수 없는 위치입니다.");
        }
    }
}
