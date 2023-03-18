package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
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
class QueenTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final Queen queen = new Queen(BLACK);

        final List<Square> route = queen.findRoute(new Square(A, ONE), new Square(H, EIGHT));

        assertThat(route).containsExactly(
                new Square(B, TWO),
                new Square(C, THREE),
                new Square(D, FOUR),
                new Square(E, FIVE),
                new Square(F, SIX),
                new Square(G, SEVEN),
                new Square(H, EIGHT)
        );
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final Queen queen = new Queen(BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> queen.findRoute(new Square(D, FOUR), new Square(G, FIVE)))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }
}
