import domain.ChessBoard;
import fixture.PieceImpl;
import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("포인트에 기물이 있으면 기물을 반환한다.")
    void find_piece_with_point() {
        final var point = new Point(File.F, Rank.ONE);
        final var color = Color.BLACK;
        List<Piece> pieceList = List.of(new PieceImpl(point, color));
        final var pieces = new Pieces(pieceList);
        final var sut = new ChessBoard(pieces);

        final var result = sut.findPieceByPoint(point);

        assertThat(result).isEqualTo(new PieceImpl(point, color));
    }

    @Test
    @DisplayName("포인트에 기물이 없으면 예외를 발생한다.")
    void throw_exception_when_not_exist_point() {
        final var point = new Point(File.F, Rank.ONE);
        final var color = Color.BLACK;
        List<Piece> pieceList = List.of(new PieceImpl(point, color));
        final var pieces = new Pieces(pieceList);
        final var sut = new ChessBoard(pieces);
        final var notExistedPoint = new Point(File.D, Rank.FOUR);

        Assertions.assertThatThrownBy(() -> sut.findPieceByPoint(notExistedPoint))
                  .isInstanceOf(IllegalArgumentException.class);
    }
}
