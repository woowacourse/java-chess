package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.File;
import chess.domain.board.Move;
import chess.domain.board.Rank;
import chess.domain.board.Square;
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
            final Pawn pawn = new Pawn(Team.WHITE);
            final Move move = Move.UP;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Team.WHITE);
            final Move move = Move.UP;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 이상 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Team.WHITE, true);
            final Move move = Move.UP;

            assertThat(pawn.isMovable(source, target, move)).isFalse();
        }

        @DisplayName("폰은 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            final Square source = new Square(File.A, Rank.TWO);
            final Square target = new Square(File.B, Rank.THREE);
            final Pawn pawn = new Pawn(Team.WHITE);
            final Move move = Move.RIGHT_UP;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            final Square source = new Square(File.B, Rank.TWO);
            final Square target = new Square(File.A, Rank.THREE);
            final Pawn pawn = new Pawn(Team.WHITE);
            final Move move = Move.LEFT_UP;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
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
            final Pawn pawn = new Pawn(Team.BLACK);
            final Move move = Move.DOWN;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square target = new Square(File.A, Rank.FIVE);
            final Pawn pawn = new Pawn(Team.BLACK);
            final Move move = Move.DOWN;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 이상 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            final Square source = new Square(File.A, Rank.SIX);
            final Square target = new Square(File.A, Rank.FOUR);
            final Pawn pawn = new Pawn(Team.BLACK, true);
            final Move move = Move.DOWN;

            assertThat(pawn.isMovable(source, target, move)).isFalse();
        }

        @DisplayName("폰은 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            final Square source = new Square(File.B, Rank.SEVEN);
            final Square target = new Square(File.A, Rank.SIX);
            final Pawn pawn = new Pawn(Team.BLACK);
            final Move move = Move.LEFT_DOWN;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }

        @DisplayName("폰은 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square target = new Square(File.B, Rank.SIX);
            final Pawn pawn = new Pawn(Team.BLACK);
            final Move move = Move.RIGHT_DOWN;

            assertThat(pawn.isMovable(source, target, move)).isTrue();
        }
    }
}
