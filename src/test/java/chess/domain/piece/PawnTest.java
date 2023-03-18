package chess.domain.piece;

import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Nested
    class findRoute_메서드는 {

        @Nested
        class 검은색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);
                final Square source = new Square(E, SEVEN);
                final Square destination = new Square(F, SIX);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(F, SIX));
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);
                final Square source = new Square(E, SEVEN);
                final Square destination = new Square(E, SIX);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(E, SIX));
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);
                final Square source = new Square(E, SEVEN);
                final Square destination = new Square(E, FIVE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(E, FIVE));
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(BLACK);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(new Square(E, SEVEN), new Square(E, FOUR)))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }

        @Nested
        class 흰색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);
                final Square source = new Square(E, TWO);
                final Square destination = new Square(F, THREE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(F, THREE));
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);
                final Square source = new Square(E, TWO);
                final Square destination = new Square(E, THREE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(E, THREE));
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);
                final Square source = new Square(E, TWO);
                final Square destination = new Square(E, FOUR);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(E, FOUR));
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(WHITE);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(new Square(F, TWO), new Square(G, FOUR)))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }
    }
}
