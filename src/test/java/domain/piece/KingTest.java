package domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.nonpawn.King;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class KingTest {
    private final Piece king = new King(Color.WHITE);
    private final Piece other = Empty.create();

    // todo 방향 테스트

    @Test
    void 한_칸만_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);

        assertThatCode(() -> king.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 한_칸_이상_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.FOUR);

        assertThatThrownBy(() -> king.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 1칸 이동할 수 있습니다");
    }
}
