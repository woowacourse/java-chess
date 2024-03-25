package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class BlackPawnTest {
    private final Pawn blackPawn = new BlackPawn();

    @Test
    void 뒤로_움직이는지_확인한다() {
        Position source = new Position(File.D, Rank.SEVEN);
        Position target = new Position(File.D, Rank.EIGHT);

        boolean actual = blackPawn.isMovedBack(source, target);

        assertThat(actual).isTrue();
    }
}
