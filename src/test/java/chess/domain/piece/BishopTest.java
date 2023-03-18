package chess.domain.piece;

import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.SEVEN;
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
class BishopTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final Bishop rook = new Bishop(BLACK);

        final List<Square> route = rook.findRoute(new Square(H, EIGHT), new Square(E, FIVE));

        assertThat(route).containsExactly(
                new Square(G, SEVEN),
                new Square(F, SIX),
                new Square(E, FIVE)
        );
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final Rook rook = new Rook(BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> rook.findRoute(new Square(H, EIGHT), new Square(D, FIVE)))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }

}
