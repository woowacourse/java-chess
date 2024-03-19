import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.point.File;
import domain.piece.point.Point;
import domain.piece.point.Rank;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("기물 목록을 포함한 일급컬렉션을 생성한다.")
    void create_with_PieceList() {
        final var point1 = new Point(File.F, Rank.ONE);
        final var color1 = Color.BLACK;

        final var point2 = new Point(File.F, Rank.ONE);
        final var color2 = Color.BLACK;
        List<Piece> pieceList = List.of(new PieceImpl(point1, color1), new PieceImpl(point2, color2));

        final var sut = new Pieces(pieceList);

        assertThat(sut).isInstanceOf(Pieces.class);
    }

}
