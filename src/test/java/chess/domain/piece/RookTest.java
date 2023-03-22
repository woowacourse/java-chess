package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.util.SquareFixture.E_FOUR;
import static chess.util.SquareFixture.E_SIX;
import static chess.util.SquareFixture.E_THREE;
import static chess.util.SquareFixture.F_THREE;
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
class RookTest {

    @Nested
    class isMovable_메서드는 {

        @Test
        void 경로를_만들_수_없는_칸이면_예외를_던진다() {
            final Rook rook = new Rook(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(F_THREE, rook));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> rook.isMovable(F_THREE, E_FOUR, boardSnapShot))
                    .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }

        @Test
        void 경로를_만들_수_있고_갈_수_있다면_true_반환한다() {
            final Rook rook = new Rook(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_THREE, rook));

            assertThat(rook.isMovable(E_THREE, E_SIX, boardSnapShot)).isTrue();
        }

        @Test
        void 경로를_만들_수_있고_갈_수_없다면_false_반환한다() {
            final Rook rook = new Rook(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                    E_THREE, rook,
                    E_SIX, new Rook(BLACK)
            ));

            assertThat(rook.isMovable(E_THREE, E_SIX, boardSnapShot)).isFalse();
        }
    }

    @Test
    void 룩은_폰이_아니다() {
        final Rook rook = new Rook(BLACK);

        assertThat(rook.isPawn()).isFalse();
    }
}
