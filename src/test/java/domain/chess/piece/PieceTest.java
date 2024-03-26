package domain.chess.piece;

import domain.chess.Color;
import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;

import java.util.List;

import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

    @Test
    @DisplayName("좌표와 색깔을 통해 기물을 생성한다,")
    void create_with_point_and_color() {
        final Piece piece = new PieceImpl(new Point(File.A, Rank.ONE), Color.BLACK);

        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("기물과 포인트 사이에 기물이 있으면 참을 반환한다")
    void true_if_piece_to_point_path_have_any_piece() {
        final Piece piece = new Bishop(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(C3, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasAnyPieceInPath(H8, pieces);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("기물과 포인트 사이에 기물이 없으면 거짓을 반환한다")
    void false_if_piece_to_point_path_does_not_have_any_piece() {
        final Piece piece = new Rook(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(F1, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasAnyPieceInPath(C3, pieces);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("기물과 포인트 사이가 갈 수 없으면 예외를 발생한다")
    void throw_exception_when_non_direction_that_piece_to_point() {
        final Piece piece = new Rook(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(F1, Color.WHITE), new Knight(F2, Color.BLACK)));

                assertThatThrownBy(() -> piece.hasAnyPieceInPath(C4, pieces))
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트에 아군 기물이 있으면 참을 반환한다")
    void true_if_friend_piece_in_point() {
        final Piece piece = new Rook(F4, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(F1, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasFriendPiece(F2, pieces);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("포인트에 적 기물 또는 비어있을 시 거짓을 반환한다")
    void false_if_enemy_piece_or_empty_in_point() {
        final Piece piece = new Bishop(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(C3, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasFriendPiece(C3, pieces);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("포인트에 어떤 기물이든 있으면 참을 반환한다")
    void true_if_any_piece_in_point() {
        final Piece piece = new Bishop(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(C3, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasAnyPiece(C3, pieces);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("포인트에 어떤 기물이든 있으면 참을 반환한다")
    void false_if_any_piece_not_in_point() {
        final Piece piece = new Bishop(A1, Color.BLACK);
        final Pieces pieces = new Pieces(List.of(new King(C3, Color.WHITE), new Knight(F2, Color.BLACK)));

        final var result = piece.hasAnyPiece(F6, pieces);

        assertThat(result).isFalse();
    }
}
