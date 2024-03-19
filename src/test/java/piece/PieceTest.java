package piece;

import domain.piece.Color;
import domain.piece.Piece;
import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.piece.point.File;
import domain.piece.point.Point;
import domain.piece.point.Rank;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("좌표와 색깔을 통해 기물을 생성한다,")
    void create_with_point_and_color() {
        Piece piece = new PieceImpl(new Point(File.A, Rank.ONE), Color.BLACK);

        assertThat(piece).isInstanceOf(Piece.class);
    }
}
