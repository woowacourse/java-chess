package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Nested
    class findRoute_메서드는 {

        @Nested
        class 검은색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.BLACK);
                final Square source = new Square(File.E, Rank.SEVEN);
                final Square destination = new Square(File.F, Rank.SIX);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.F, Rank.SIX));
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.BLACK);
                final Square source = new Square(File.E, Rank.SEVEN);
                final Square destination = new Square(File.E, Rank.SIX);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.E, Rank.SIX));
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.BLACK);
                final Square source = new Square(File.E, Rank.SEVEN);
                final Square destination = new Square(File.E, Rank.FIVE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.E, Rank.FIVE));
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(Color.BLACK);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(new Square(File.E, Rank.SEVEN), new Square(File.E, Rank.FOUR)))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }

        @Nested
        class 흰색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.WHITE);
                final Square source = new Square(File.E, Rank.TWO);
                final Square destination = new Square(File.F, Rank.THREE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.F, Rank.THREE));
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.WHITE);
                final Square source = new Square(File.E, Rank.TWO);
                final Square destination = new Square(File.E, Rank.THREE);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.E, Rank.THREE));
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(Color.WHITE);
                final Square source = new Square(File.E, Rank.TWO);
                final Square destination = new Square(File.E, Rank.FOUR);

                assertThat(pawn.findRoute(source, destination)).containsExactly(new Square(File.E, Rank.FOUR));
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(Color.WHITE);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(new Square(File.F, Rank.TWO), new Square(File.G, Rank.FOUR)))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }
    }
}
