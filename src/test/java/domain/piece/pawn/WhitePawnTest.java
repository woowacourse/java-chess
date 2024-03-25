package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class WhitePawnTest {
    private final Pawn whitePawn = new WhitePawn();

    @Test
    void 뒤로_움직이는지_확인한다() {
        Position source = new Position(File.D, Rank.TWO);
        Position target = new Position(File.D, Rank.ONE);

        boolean actual = whitePawn.isMovedBack(source, target);

        assertThat(actual).isTrue();
    }
}
