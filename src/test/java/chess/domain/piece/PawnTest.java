package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
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
            Square source = new Square(File.A, Rank.TWO);
            Square target = new Square(File.A, Rank.THREE);
            Pawn pawn = new Pawn(Team.WHITE);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            Square source = new Square(File.A, Rank.TWO);
            Square target = new Square(File.A, Rank.FOUR);
            Pawn pawn = new Pawn(Team.WHITE);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 이상 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            Square source = new Square(File.A, Rank.TWO);
            Square target = new Square(File.A, Rank.FOUR);
            Pawn pawn = new Pawn(Team.WHITE, true);

            assertThatThrownBy(() -> pawn.validateMovableRange(source, target))
                    .isInstanceOf(PieceCanNotMoveException.class)
                    .hasMessage("이동할 수 없는 말입니다.");
        }

        @DisplayName("폰은 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            Square source = new Square(File.A, Rank.TWO);
            Square target = new Square(File.B, Rank.THREE);
            Pawn pawn = new Pawn(Team.WHITE);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            Square source = new Square(File.B, Rank.TWO);
            Square target = new Square(File.A, Rank.THREE);
            Pawn pawn = new Pawn(Team.WHITE);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }
    }

    @DisplayName("폰이 검은색 진영이다.")
    @Nested
    class BlackPawnTest {
        @DisplayName("폰은 앞으로 한 칸 움직일 수 있다.")
        @Test
        void Pawn1() {
            Square source = new Square(File.A, Rank.THREE);
            Square target = new Square(File.A, Rank.TWO);
            Pawn pawn = new Pawn(Team.BLACK);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 움직인 적이 없을 경우 두 칸 움직일 수 있다.")
        @Test
        void Pawn2() {
            Square source = new Square(File.A, Rank.SEVEN);
            Square target = new Square(File.A, Rank.FIVE);
            Pawn pawn = new Pawn(Team.BLACK);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 움직인 적이 있을 경우 두 칸 이상 움직일 수 없다.")
        @Test
        void Pawn2_1() {
            Square source = new Square(File.A, Rank.SIX);
            Square target = new Square(File.A, Rank.FOUR);
            Pawn pawn = new Pawn(Team.BLACK, true);

            assertThatThrownBy(() -> pawn.validateMovableRange(source, target))
                    .isInstanceOf(PieceCanNotMoveException.class)
                    .hasMessage("이동할 수 없는 말입니다.");
        }

        @DisplayName("폰은 오른쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn3() {
            Square source = new Square(File.B, Rank.SEVEN);
            Square target = new Square(File.A, Rank.SIX);
            Pawn pawn = new Pawn(Team.BLACK);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }

        @DisplayName("폰은 왼쪽 대각선으로 움직일 수 있다.")
        @Test
        void Pawn4() {
            Square source = new Square(File.A, Rank.SEVEN);
            Square target = new Square(File.B, Rank.SIX);
            Pawn pawn = new Pawn(Team.BLACK);

            assertDoesNotThrow(() -> pawn.validateMovableRange(source, target));
        }
    }
}
