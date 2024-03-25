package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class NonPawnTest {
    private final Piece nonPawn = new Queen(Color.WHITE);

    @Test
    void 같은_색의_기물이_있으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.EIGHT);
        Position target = new Position(File.F, Rank.FOUR);
        Piece other = new Bishop(Color.WHITE);

        assertThatThrownBy(() -> nonPawn.validateMovement(source, target, other))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 색의 기물이 존재합니다");
    }

    @Test
    void 다른_색의_기물이_있으면_예외가_발생하지_않는다() {
        Position source = new Position(File.F, Rank.EIGHT);
        Position target = new Position(File.F, Rank.FOUR);
        Piece other = new Bishop(Color.BLACK);

        assertThatCode(() -> nonPawn.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }
}
