package domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.nonpawn.Bishop;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class BishopTest {
    private final Piece bishop = new Bishop(Color.WHITE);
    private final Piece other = Empty.create();

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.G, Rank.SEVEN);

        assertThatCode(() -> bishop.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 직선_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.EIGHT);

        assertThatThrownBy(() -> bishop.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 방향으로 이동해야 합니다.");
    }
}
