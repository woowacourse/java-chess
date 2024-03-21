import domain.*;
import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;

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

    @Test
    @DisplayName("위치에 있는 기물이 아군이면 거짓으로 반환한다.")
    void false_if_piece_in_point_is_friend() {
        final var sut = new Pieces(List.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.B, Rank.FIVE), Color.BLACK),
                new Queen(new Point(File.C, Rank.FIVE), Color.WHITE)));

        final var piece = sut.getPieceWithPoint(new Point(File.C, Rank.FIVE))
                             .get();

        assertThat(sut.isFriend(piece, new Point(File.B, Rank.FIVE))).isFalse();
    }

    @Test
    @DisplayName("위치에 있는 기물이 아군이면 참으로 반환한다.")
    void true_if_piece_in_point_is_friend() {
        final var sut = new Pieces(List.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.B, Rank.FIVE), Color.WHITE),
                new Queen(new Point(File.C, Rank.FIVE), Color.WHITE)));

        final var piece = sut.getPieceWithPoint(new Point(File.C, Rank.FIVE))
                             .get();

        assertThat(sut.isFriend(piece, new Point(File.B, Rank.FIVE))).isTrue();
    }

    @Test
    @DisplayName("포인트에서 포인트 까지 기물이 있으면 참을 반환한다.")
    void true_if_exist_piece_in_start_point_to_end_point() {
        final var sut = new Pieces(List.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var result = sut.hasAnyPiece(new Point(File.A, Rank.ONE), new Point(File.E, Rank.FIVE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("포인트에서 포인트 까지 기물이 없으면 거짓을 반환한다.")
    void false_if_not_exist_piece_in_start_point_to_end_point() {
        final var sut = new Pieces(List.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var result = sut.hasAnyPiece(new Point(File.A, Rank.ONE), new Point(File.C, Rank.THREE));

        assertThat(result).isFalse();
    }
}
