package chess.domain.piece;

import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.SIX;
import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Square;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final Knight knight = new Knight(BLACK);

        final List<Square> route = knight.findRoute(new Square(E, FOUR), new Square(F, SIX));

        assertThat(route).containsExactly(new Square(F, SIX));
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final Knight knight = new Knight(BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> knight.findRoute(new Square(E, FOUR), new Square(G, SIX)))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }
}
