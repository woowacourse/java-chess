package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰이 흰색 진영이다.")
    @Nested
    class WhitePawnTest {
        @DisplayName("폰은 앞으로 한 칸 움직일 수 있다.")
        @Test
        void Pawn1() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.A, Rank.THREE);
            final Pawn pawn = new Pawn(Camp.WHITE);

            assertThat(pawn.isMovable(source, target)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 앞으로 한 칸 초과 움직일 수 없다.")
        @Test
        void Pawn1_1() {
            final Square source = new Square(File.A, Rank.THREE);
            final Square target = new Square(File.A, Rank.FIVE);
            final Pawn pawn = new Pawn(Camp.WHITE, true);

            assertThat(pawn.isMovable(source, target)).isFalse();
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Camp.WHITE);

            assertThat(pawn.isMovable(source, target)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Camp.WHITE, true);

            assertThat(pawn.isMovable(source, target)).isFalse();
        }

        @DisplayName("폰은 오른쪽 대각선에 상대 말이 존재할 경우 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.B, Rank.THREE);
            final Pawn pawn = new Pawn(Camp.WHITE);

            assertThat(pawn.isMovable(source, target, true)).isTrue();
        }

        @DisplayName("폰은 왼쪽 대각선에 상대 말이 존재할 경우 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            final Square source = new Square(File.B, Rank.TWO);
            final Square target = new Square(File.A, Rank.THREE);
            final Pawn pawn = new Pawn(Camp.WHITE);

            assertThat(pawn.isMovable(source, target, false)).isTrue();
        }
    }

    @DisplayName("폰이 검은색 진영이다.")
    @Nested
    class BlackPawnTest {
        @DisplayName("폰은 앞으로 한 칸 움직일 수 있다.")
        @Test
        void Pawn1() {
            final Square source = new Square(File.A, Rank.THREE);
            final Square target = new Square(File.A, Rank.TWO);
            final Pawn pawn = new Pawn(Camp.BLACK);

            assertThat(pawn.isMovable(source, target)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 앞으로 한 칸 초과 움직일 수 없다.")
        @Test
        void Pawn1_1() {
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square target = new Square(File.A, Rank.FIVE);
            final Pawn pawn = new Pawn(Camp.BLACK, true);

            assertThat(pawn.isMovable(source, target)).isFalse();
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square target = new Square(File.A, Rank.FIVE);
            final Pawn pawn = new Pawn(Camp.BLACK);

            assertThat(pawn.isMovable(source, target)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            final Square source = new Square(File.A, Rank.SIX);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Camp.BLACK, true);

            assertThat(pawn.isMovable(source, target)).isFalse();
        }

        @DisplayName("폰은 오른쪽 대각선에 상대 말이 존재할 경우 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            final Square source = new Square(File.B, Rank.SEVEN);
            final Square target = new Square(File.A, Rank.SIX);
            final Pawn pawn = new Pawn(Camp.BLACK);

            assertThat(pawn.isMovable(source, target, true)).isTrue();
        }

        @DisplayName("폰은 왼쪽 대각선에 상대 말이 존재할 경우 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square target = new Square(File.B, Rank.SIX);
            final Pawn pawn = new Pawn(Camp.BLACK);

            assertThat(pawn.isMovable(source, target, false)).isTrue();
        }
    }
}
