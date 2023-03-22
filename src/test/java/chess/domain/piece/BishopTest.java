package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.util.SquareFixture.D_FIVE;
import static chess.util.SquareFixture.E_FIVE;
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
class BishopTest {

    @Nested
    class isMovable_메서드는 {

        @Test
        void 경로를_만들_수_없는_칸이면_예외를_던진다() {
            final Bishop bishop = new Bishop(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(H_EIGHT, bishop));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> bishop.isMovable(H_EIGHT, D_FIVE, boardSnapShot))
                    .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }

        @Test
        void 경로를_만들_수_있고_갈_수_있다면_true_반환한다() {
            final Bishop bishop = new Bishop(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(H_EIGHT, bishop));

            assertThat(bishop.isMovable(H_EIGHT, E_FIVE, boardSnapShot)).isTrue();
        }

        @Test
        void 경로를_만들_수_있고_갈_수_없다면_false_반환한다() {
            final Bishop bishop = new Bishop(BLACK);
            final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                    H_EIGHT, bishop,
                    E_FIVE, new Rook(BLACK)
            ));

            assertThat(bishop.isMovable(H_EIGHT, E_FIVE, boardSnapShot)).isFalse();
        }
    }
}
