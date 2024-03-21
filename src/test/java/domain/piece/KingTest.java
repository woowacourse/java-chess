package domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class KingTest {
    private final Piece king = new King(Color.WHITE);

    @Test
    void 한_칸만_이동할_수_있다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new Pawn(Color.BLACK);

        assertThatCode(() -> king.validateMovement(resource, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 한_칸_이상_이동하면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.FOUR);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> king.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("King은 한 번에 1칸만 이동할 수 있습니다");
    }

    @Test
    void 이동하지_않으면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FOUR);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> king.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("King은 한 번에 1칸만 이동할 수 있습니다.");
    }

    @Test
    void 같은_팀의_말을_잡으면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> king.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
    }
}
