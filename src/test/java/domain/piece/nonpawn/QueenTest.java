package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class QueenTest {
    private final Piece queen = new Queen(Color.WHITE);
    private final Piece other = Empty.create();

    @Test
    void 수직_방향으로_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FIVE);

        assertThatCode(() -> queen.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 수평_방향으로_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.C, Rank.FOUR);

        assertThatCode(() -> queen.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);

        assertThatCode(() -> queen.validateMovement(source, target, other))
                .doesNotThrowAnyException();

    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.THREE);

        assertThatThrownBy(() -> queen.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.A, Rank.TWO);

        assertThatThrownBy(() -> queen.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 거리에_상관없이_이동할_수_있다() {
        Position source = new Position(File.D, Rank.ONE);
        Position target = new Position(File.D, Rank.EIGHT);

        assertThatCode(() -> queen.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }
}
