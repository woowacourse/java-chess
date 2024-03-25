package domain.chess.piece;

import domain.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩은 오른쪽 방향으로 이동할 수 있다.")
    void can_move_right() {
        final var sut = new Rook(C4, Color.BLACK);

        final var result = sut.canMove(E4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 아래 방향으로 이동할 수 있다.")
    void can_move_down() {
        final var sut = new Rook(C4, Color.BLACK);

        final var result = sut.canMove(C1);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 위 방향으로 이동할 수 있다.")
    void can_move_up() {
        final var sut = new Rook(C2, Color.BLACK);

        final var result = sut.canMove(C8);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 왼쪽 방향으로 이동할 수 있다.")
    void can_move_left() {
        final var sut = new Rook(F4, Color.BLACK);

        final var result = sut.canMove(A4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Rook(F8, Color.BLACK);

        final var result = sut.canMove(E7);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("룩은 가는길에 기물이 없으면 참을 반환한다.")
    void true_if_rook_piece_can_move() {
        final var pieceList = List.of(
                new Rook(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E1, WHITE));

        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(A6, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("룩은 가는길에 기물이 있으면 거짓을 반환한다.")
    void false_if_rook_piece_can_move() {
        final var pieceList = List.of(
                new Rook(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E5, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(E1, pieceList);
        assertThat(result).isFalse();
    }
}
