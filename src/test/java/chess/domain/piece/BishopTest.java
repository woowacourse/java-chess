package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final Bishop rook = new Bishop(Color.BLACK);

        final List<Square> route = rook.findRoute(new Square(File.H, Rank.EIGHT), new Square(File.E, Rank.FIVE));

        assertThat(route).containsExactly(
                new Square(File.G, Rank.SEVEN),
                new Square(File.F, Rank.SIX),
                new Square(File.E, Rank.FIVE)
        );
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final Rook rook = new Rook(Color.BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> rook.findRoute(new Square(File.H, Rank.EIGHT), new Square(File.D, Rank.FIVE)))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }

}
