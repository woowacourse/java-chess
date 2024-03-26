package domain.chess.piece;


import domain.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트는 위 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_up_and_up_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(E6);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 위 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_up_and_up_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(C6);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 위 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_up_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(B5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 왼쪽 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_left_and_down_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 왼쪽 으로 이동 가능하다.")
    void can_move_down_and_down_left() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(C2);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 아래 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_down_and_down_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(E2);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 위 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_up_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(F5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 오른쪽 - 아래 오른쪽 으로 이동 가능하다.")
    void can_move_right_and_down_right() {
        final var sut = new Knight(D4, Color.BLACK);

        final var result = sut.canMove(F3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case1() {
        final var sut = new Knight(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("나이트는 기존 기물들(비숍,퀸,룩) 과 같이 이동 불가능하다.")
    void can_not_move_like_other_piece_case2() {
        final var sut = new Knight(F4, Color.BLACK);

        final var result = sut.canMove(A4);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 적 기물이 있으면 참을 반환한다.")
    void true_if_knight_piece_move_not_existed_enemy_point() {
        final var pieceList = List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(C2, pieceList);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("나이트는 도착하는 위치에 기물이 없으면 참을 반환한다.")
    void true_if_knight_piece_move_not_existed_empty_point() {
        final var pieceList = List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(B3, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_knight_piece_move_friend_point() {
        final var pieceList = List.of(
                new Knight(A1, BLACK),
                new Queen(C2, BLACK));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(C2, pieceList);
        assertThat(result).isFalse();
    }
}
