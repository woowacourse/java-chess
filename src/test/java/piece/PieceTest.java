package piece;

import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piece.point.File;
import piece.point.Point;
import piece.point.Rank;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("좌표를 통해 기물을 생성한다,")
    void create_with_point() {
        Piece piece = new PieceImpl(new Point(File.A, Rank.ONE));

        assertThat(piece).isInstanceOf(Piece.class);
    }
}
