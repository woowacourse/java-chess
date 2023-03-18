package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.B_TWO;
import static chess.util.SquareFixture.C_THREE;
import static chess.util.SquareFixture.D_FOUR;
import static chess.util.SquareFixture.E_FIVE;
import static chess.util.SquareFixture.F_SIX;
import static chess.util.SquareFixture.G_FIVE;
import static chess.util.SquareFixture.G_SEVEN;
import static chess.util.SquareFixture.H_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Square;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QueenTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final Queen queen = new Queen(BLACK);

        final List<Square> route = queen.findRoute(A_ONE, H_EIGHT);

        assertThat(route).containsExactly(B_TWO, C_THREE, D_FOUR, E_FIVE, F_SIX, G_SEVEN, H_EIGHT);
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final Queen queen = new Queen(BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> queen.findRoute(D_FOUR, G_FIVE))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }
}
