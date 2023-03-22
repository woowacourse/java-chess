package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.D_FOUR;
import static chess.util.SquareFixture.G_FIVE;
import static chess.util.SquareFixture.H_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.BoardSnapShot;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QueenTest {

    @Nested
    class isMovable_메서드는 {

        @Test
        void 경로를_만들_수_없는_칸이면_예외를_던진다() {
            final Queen queen = new Queen(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(D_FOUR, queen));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> queen.isMovable(D_FOUR, G_FIVE, boardSnapShot))
                    .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }

        @Test
        void 경로를_만들_수_있고_갈_수_있다면_true_반환한다() {
            final Queen queen = new Queen(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(A_ONE, queen));

            assertThat(queen.isMovable(A_ONE, H_EIGHT, boardSnapShot)).isTrue();
        }

        @Test
        void 경로를_만들_수_있고_갈_수_없다면_false_반환한다() {
            final Queen queen = new Queen(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                    A_ONE, queen,
                    H_EIGHT, new Rook(BLACK)
            ));

            assertThat(queen.isMovable(A_ONE, H_EIGHT, boardSnapShot)).isFalse();
        }
    }

    @Test
    void 퀸은_폰이_아니다() {
        final Queen queen = new Queen(BLACK);

        assertThat(queen.isPawn()).isFalse();
    }
}
