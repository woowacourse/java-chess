package domain.chess.piece;

import domain.chess.Color;
import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    @DisplayName("킹은 아래 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(new Point(File.D, Rank.FOUR));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new King(C2, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 오른쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_right() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(D4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 아래 방향으로 한칸 이동할 수 있다.")
    void can_move_down() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(C3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 위 방향으로 한칸 이동할 수 있다.")
    void can_move_up() {
        final var sut = new King(C2, Color.BLACK);

        final var result = sut.canMove(C3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽 방향으로 한칸 이동할 수 있다.")
    void can_move_left() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(E4);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double() {
        final var sut = new King(F4, Color.BLACK);

        final var result = sut.canMove(D4);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 한칸 이상은 이동할 수 없다.")
    void can_not_move_double_case() {
        final var sut = new King(C4, Color.BLACK);

        final var result = sut.canMove(A2);

        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_king_piece_move_friend_point() {
        final var pieceList = List.of(
                new King(A1, BLACK),
                new Queen(B2, BLACK));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(B2, pieceList);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 아니면 참을 반환한다.")
    void true_if_king_piece_move_friend_point() {

        final var pieceList = List.of(
                new King(A1, BLACK),
                new Queen(B2, WHITE));

        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(B2, pieceList);
        assertThat(result).isTrue();
    }

}
