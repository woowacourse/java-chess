package domain.chess.piece;

import domain.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    @DisplayName("퀸은 아래 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new Queen(C4, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 아래 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new Queen(C4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new Queen(C2, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new Queen(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 오른쪽 방향으로 이동할 수 있다.")
    void can_move_right() {
        final var sut = new Queen(C4, Color.BLACK);

        final var result = sut.canMove(E4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 아래 방향으로 이동할 수 있다.")
    void can_move_down() {
        final var sut = new Queen(C4, Color.BLACK);

        final var result = sut.canMove(C1);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 위 방향으로 이동할 수 있다.")
    void can_move_up() {
        final var sut = new Queen(C2, Color.BLACK);

        final var result = sut.canMove(C8);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 왼쪽 방향으로 이동할 수 있다.")
    void can_move_left() {
        final var sut = new Queen(F4, Color.BLACK);

        final var result = sut.canMove(A4);

        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("퀸은 가는길에 기물이 없으면 참을 반환한다.")
    void true_if_queen_piece_can_move() {
        final var pieceList = List.of(
                new Queen(A1, BLACK),
                new Bishop(C1, WHITE),
                new Queen(E1, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(A3, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸은 가는길에 기물이 있으면 거짓을 반환한다.")
    void false_if_queen_piece_can_move() {
        final var pieceList = List.of(
                new Queen(A1, BLACK),
                new Bishop(C3, WHITE),
                new Queen(E5, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(E5, pieceList);
        assertThat(result).isFalse();
    }


}
