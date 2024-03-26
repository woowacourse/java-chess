package domain.piece;

import domain.piece.kind.*;
import domain.piece.kind.jumping.King;
import domain.piece.kind.jumping.Knight;
import domain.piece.kind.sliding.Bishop;
import domain.piece.kind.sliding.Queen;
import domain.piece.kind.sliding.Rook;
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
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_bishop_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Bishop(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.E, Rank.FIVE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_rook_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.ONE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.E, Rank.ONE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_queen_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Queen(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.E, Rank.FIVE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 퀸이 특정 기물들 사이에서 이동할 수 있으면 참을 반환한다.")
    void true_if_queen_piece_can_move() {

        final var sut = new Pieces(List.of(
                new Queen(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.ONE), Color.WHITE),
                new Queen(new Point(File.E, Rank.ONE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.A, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_knight_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new Knight(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.C, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.C, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_king_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new King(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.B, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.B, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 아니면 참을 반환한다.")
    void true_if_king_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new King(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.B, Rank.TWO), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.check(piece, new Point(File.B, Rank.TWO));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_point_in_any_piece() {
        final var sut = new Pieces(List.of(
                new Pawn(new Point(File.A, Rank.FOUR), Color.BLACK),
                new Queen(new Point(File.A, Rank.THREE), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.FOUR))
                             .get();

        final var result = sut.check(piece, new Point(File.A, Rank.THREE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 없으면 참을 반환한다")
    void true_if_pawn_piece_move_point_not_in_piece() {
        final var sut = new Pieces(List.of(
                new Pawn(new Point(File.A, Rank.FOUR), Color.BLACK),
                new Queen(new Point(File.A, Rank.ONE), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.FOUR))
                             .get();

        final var result = sut.check(piece, new Point(File.A, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 적 기물이 있으면 참을 반환한다.")
    void true_if_pawn_piece_move_diagonal_enemy_point() {
        final var sut = new Pieces(List.of(
                new Pawn(new Point(File.A, Rank.FOUR), Color.BLACK),
                new Queen(new Point(File.B, Rank.THREE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.FOUR))
                             .get();

        final var result = sut.check(piece, new Point(File.B, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_diagonal_enemy_point() {
        final var sut = new Pieces(List.of(
                new Pawn(new Point(File.A, Rank.FOUR), Color.BLACK),
                new Queen(new Point(File.B, Rank.THREE), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.FOUR))
                             .get();

        final var result = sut.check(piece, new Point(File.B, Rank.THREE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("기물을 해당 좌표로 이동시킨다.")
    void move_piece_to_point() {
        final var sut = new Pieces(List.of(
                new Knight(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.C, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        sut.move(piece, new Point(File.B, Rank.THREE));

        final var findPiece = sut.findPieceWithPoint(new Point(File.B, Rank.THREE))
                                 .get();
        assertThat(piece).isEqualTo(findPiece);
    }

    @Test
    @DisplayName("기물이 이동할 때 해당 좌표에 적 기물이 있으면 제거한다.")
    void move_piece_with_remove_if_exist_enemy_piece() {
        final var sut = new Pieces(List.of(
                new Knight(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.C, Rank.TWO), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        sut.move(piece, new Point(File.C, Rank.TWO));

        assertThat(sut.size()).isEqualTo(1);
    }
}
