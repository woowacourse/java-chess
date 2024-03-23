package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    private final Piece knight = new Knight(Color.WHITE);

    @Test
    void L자_방향으로_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.THREE);
        Piece other = new BlackPawn();

        assertThatCode(() -> knight.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.G, Rank.SEVEN);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> knight.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트는 L자 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 직선_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.EIGHT);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> knight.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트는 L자 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.A, Rank.TWO);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> knight.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트는 L자 방향으로만 이동할 수 있습니다.");
    }

    @Test
    void 이동하지_않으면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FOUR);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> knight.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("동일한 위치입니다.");
    }

    @Test
    void 같은_팀의_말을_잡으면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new WhitePawn();

        assertThatThrownBy(() -> knight.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
    }
}
