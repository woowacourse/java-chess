package domain.chess.piece;

import domain.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("검은색 폰은 아래로 한칸 이동할 수 있다.")
    void black_pawn_can_move_down() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(C3);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 위로 한칸 이동할 수 없다.")
    void black_pawn_can_not_move_down() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(C5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검은색 폰은 아래 오른쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_right() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(D3, List.of(new Pawn(D3, WHITE)));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검은색 폰은 아래 왼쪽 방향으로 대각선 이동할 수 있다.")
    void black_pawn_can_move_down_left() {
        final var sut = new Pawn(C4, Color.BLACK);

        final var result = sut.canMove(B3, List.of(new Pawn(B3, WHITE)));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 위로 한칸 이동할 수 있다.")
    void white_pawn_can_move_up() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(C5);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 아래로 한칸 이동할 수 없다.")
    void white_pawn_can_move_down() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(C3);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("하얀색 폰은 위 방향으로 대각선 이동할 수 있다.")
    void white_pawn_can_move_up_left() {
        final var sut = new Pawn(C4, Color.WHITE);

        final var result = sut.canMove(B5, List.of(new Pawn(B5, BLACK)));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_point_in_any_piece() {
        final var pieceList = List.of(
                new Pawn(A4, BLACK),
                new Queen(A3, BLACK));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A4)
                              .get();

        final var result = sut.canMove(A3, pieceList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 직진 하는 위치에 기물이 없으면 참을 반환한다")
    void true_if_pawn_piece_move_point_not_in_piece() {
        final var pieceList = List.of(
                new Pawn(A4, BLACK),
                new Queen(A1, BLACK));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A4)
                              .get();

        final var result = sut.canMove(A3, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 적 기물이 있으면 참을 반환한다.")
    void true_if_pawn_piece_move_diagonal_enemy_point() {
        final var pieceList = List.of(
                new Pawn(A4, BLACK),
                new Queen(B3, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A4)
                              .get();

        final var result = sut.canMove(B3, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_pawn_piece_move_diagonal_enemy_point() {
        final var pieceList = List.of(
                new Pawn(A4, BLACK),
                new Queen(B3, BLACK));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A4)
                              .get();

        final var result = sut.canMove(B3, pieceList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검은색 폰이 아직 이동하지 않았다면 아래로 두칸 이동할 수 있다.")
    void black_pawn_can_move_down_double() {
        final var sut = new Pawn(C7, Color.BLACK);

        final var result = sut.canMove(C5);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void black_pawn_can_not_move_diagonal_left_double() {
        final var sut = new Pawn(C7, Color.BLACK);

        final var result = sut.canMove(A5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void black_pawn_can_not_move_diagonal_right_double() {
        final var sut = new Pawn(C7, Color.BLACK);

        final var result = sut.canMove(E5);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("흰색 폰이 아직 이동하지 않았다면 위로 두칸 이동할 수 있다")
    void white_pawn_can_move_up_double() {
        final var sut = new Pawn(C2, Color.WHITE);

        final var result = sut.canMove(C4);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void white_pawn_can_not_move_diagonal_left_double() {
        final var sut = new Pawn(C2, Color.WHITE);

        final var result = sut.canMove(A4);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선으로 두칸 이동할 수 없다.")
    void white_pawn_can_not_move_diagonal_right_double() {
        final var sut = new Pawn(C2, Color.WHITE);

        final var result = sut.canMove(E4);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰이 아직 이동하지 않았더라도 앞에 기물이 있으면 두 칸 이동할 수 없다.")
    void pawn_can_not_move_double_when_blocked_path() {
        final var sut = new Pawn(C2, Color.WHITE);

        final var result = sut.canMove(C4, List.of(new Pawn(C3, BLACK)));
        assertThat(result).isFalse();
    }
}
