package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    void 피스가_DOWN_방향으로_이동하는_경로를_반환한다() {
        Position resource = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.F, Rank.ONE);
        Piece piece = new Piece(Type.ROOK, Color.WHITE);

        List<Position> positions = piece.route(resource, target);
        assertThat(positions).hasSize(3);
        assertThat(positions).containsExactlyElementsOf(List.of(
                new Position(File.F, Rank.FOUR),
                new Position(File.F, Rank.THREE),
                new Position(File.F, Rank.TWO)
        ));
    }

    @Test
    void 피스가_DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position resource = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.D, Rank.THREE);
        Piece piece = new Piece(Type.BISHOP, Color.WHITE);

        List<Position> positions = piece.route(resource, target);
        assertThat(positions).hasSize(1);
        assertThat(positions).containsExactlyElementsOf(List.of(
                new Position(File.E, Rank.FOUR)
        ));
    }
}
