package domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class BishopTest {
    private final Piece bishop = new Bishop(Color.WHITE);

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.G, Rank.SEVEN);
        Piece other = new Pawn(Color.BLACK);

        assertThatCode(() -> bishop.validateMovement(resource, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 직선_방향으로_이동하면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.EIGHT);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> bishop.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bishop은 대각선 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.THREE);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> bishop.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bishop은 대각선 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.A, Rank.TWO);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> bishop.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bishop은 대각선 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 거리에_상관없이_이동할_수_있다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.H, Rank.EIGHT);
        Piece other = new Pawn(Color.BLACK);

        assertThatCode(() -> bishop.validateMovement(resource, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 이동하지_않으면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FOUR);
        Piece other = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> bishop.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bishop은 대각선 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 같은_팀의_말을_잡으면_예외가_발생한다() {
        Position resource = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> bishop.validateMovement(resource, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
    }
}
