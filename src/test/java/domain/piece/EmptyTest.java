package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.pawn.BlackPawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class EmptyTest {
    private final Piece empty = Empty.create();

    @Test
    void Empty_기물의_움직임을_검증하면_예외가_발생한다() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.A, Rank.TWO);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> empty.validateMovement(source, target, other))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void Empty_기물의_타입을_호출하면_예외가_발생한다() {
        assertThatThrownBy(empty::type)
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void Empty_기물의_색은_중립이다() {
        assertThat(empty.color()).isEqualTo(Color.NEUTRALITY);
    }
}
