import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import piece.Color;
import piece.Piece;
import piece.Pieces;
import piece.point.File;
import piece.point.Point;
import piece.point.Rank;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {
    @Test
    @DisplayName("기물들을 통해 체스판을 생성한다")
    void create_with_pieces() {
        final var point1 = new Point(File.F, Rank.ONE);
        final var color1 = Color.BLACK;

        final var point2 = new Point(File.F, Rank.ONE);
        final var color2 = Color.BLACK;
        List<Piece> pieceList = List.of(new PieceImpl(point1, color1), new PieceImpl(point2, color2));
        final var pieces = new Pieces(pieceList);

        final var sut = new ChessBoard(pieces);

        assertThat(sut).isInstanceOf(ChessBoard.class);
    }
}
