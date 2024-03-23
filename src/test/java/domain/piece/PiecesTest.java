package domain.piece;

import domain.piece.kind.*;
import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;

import java.util.List;

import static domain.piece.attribute.Color.BLACK;
import static domain.piece.attribute.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("기물 목록을 포함한 일급컬렉션을 생성한다.")
    void create_with_PieceList() {
        final var point1 = new Point(File.F, Rank.ONE);
        final var color1 = BLACK;

        final var point2 = new Point(File.F, Rank.ONE);
        final var color2 = BLACK;
        List<Piece> pieceList = List.of(new PieceImpl(point1, color1), new PieceImpl(point2, color2));

        final var sut = new Pieces(pieceList);

        assertThat(sut).isInstanceOf(Pieces.class);
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_bishop_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Bishop(A1, BLACK),
                new Bishop(C3, WHITE),
                new Queen(E5, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, E5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_rook_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Rook(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E5, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, E1);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_queen_piece_can_move() {
        final var sut = new Pieces(List.of(
                new Queen(A1, BLACK),
                new Bishop(C3, WHITE),
                new Queen(E5, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, E5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 있으면 참을 반환한다.")
    void true_if_rook_piece_can_move() {

        final var sut = new Pieces(List.of(
                new Rook(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E1, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, A6);
        assertThat(result).isTrue();

    }


    @Test
    @DisplayName("해당 퀸이 특정 기물들 사이에서 이동할 수 있으면 참을 반환한다.")
    void true_if_queen_piece_can_move() {

        final var sut = new Pieces(List.of(
                new Queen(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E1, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, A3);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("해당 비숍이 특정 기물들 사이에서 이동할 수 있으면 참을 반환한다.")
    void true_if_bishop_piece_can_move() {

        final var sut = new Pieces(List.of(
                new Bishop(A1, BLACK),
                new Queen(F6, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, E5);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 적 기물이 있으면 참을 반환한다.")
    void true_if_knight_piece_move_not_existed_enemy_point() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, C2);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("나이트는 도착하는 위치에 기물이 없으면 참을 반환한다.")
    void true_if_knight_piece_move_not_existed_empty_point() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, B3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_knight_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, BLACK)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, C2);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_king_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new King(A1, BLACK),
                new Queen(B2, BLACK)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, B2);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 아니면 참을 반환한다.")
    void true_if_king_piece_move_friend_point() {
        final var sut = new Pieces(List.of(
                new King(A1, BLACK),
                new Queen(B2, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        final var result = sut.check(piece, B2);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_point_in_any_piece() {
        final var sut = new Pieces(List.of(
                new Pawn(A4, BLACK),
                new Queen(A3, BLACK)));

        final var piece = sut.findPieceWithPoint(A4)
                             .get();

        final var result = sut.check(piece, A3);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 없으면 참을 반환한다")
    void true_if_pawn_piece_move_point_not_in_piece() {
        final var sut = new Pieces(List.of(
                new Pawn(A4, BLACK),
                new Queen(A1, BLACK)));

        final var piece = sut.findPieceWithPoint(A4)
                             .get();

        final var result = sut.check(piece, A3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 적 기물이 있으면 참을 반환한다.")
    void true_if_pawn_piece_move_diagonal_enemy_point() {
        final var sut = new Pieces(List.of(
                new Pawn(A4, BLACK),
                new Queen(B3, WHITE)));

        final var piece = sut.findPieceWithPoint(A4)
                             .get();

        final var result = sut.check(piece, B3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_diagonal_enemy_point() {
        final var sut = new Pieces(List.of(
                new Pawn(A4, BLACK),
                new Queen(B3, BLACK)));

        final var piece = sut.findPieceWithPoint(A4)
                             .get();

        final var result = sut.check(piece, B3);
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("기물을 해당 좌표로 이동시킨다.")
    void move_piece_to_point() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        sut.move(piece, B3);

        final var findPiece = sut.findPieceWithPoint(B3)
                                 .get();
        assertThat(piece).isEqualTo(findPiece);
    }

    @Test
    @DisplayName("기물이 이동할 때 해당 좌표에 적 기물이 있으면 제거한다.")
    void move_piece_with_remove_if_exist_enemy_piece() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        sut.move(piece, C2);

        assertThat(sut.size()).isEqualTo(1);
    }
}
