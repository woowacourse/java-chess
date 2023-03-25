package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void 움직이려는_칸까지_가는_경로를_구한다() {
        final King king = new King(Color.BLACK);

        final List<Square> route = king.findRoute(new Square(File.E, Rank.FOUR), new Square(File.F, Rank.FIVE));

        assertThat(route).containsExactly(new Square(File.F, Rank.FIVE));
    }

    @Test
    void 움직일_수_없는_칸이면_예외를_던진다() {
        final King king = new King(Color.BLACK);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> king.findRoute(new Square(File.E, Rank.FOUR), new Square(File.G, Rank.SIX)))
                .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
    }

}
