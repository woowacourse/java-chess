package domain.chess.piece;

import domain.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    @DisplayName("비숍은 아래 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_right() {
        final var sut = new Bishop(C4, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 아래 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_left() {
        final var sut = new Bishop(C4, Color.BLACK);

        final var result = sut.canMove(B3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 오른쪽 방향으로 이동할 수 있다.")
    void can_move_up_left() {
        final var sut = new Bishop(C2, Color.BLACK);

        final var result = sut.canMove(D3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 위 왼쪽 방향으로 이동할 수 있다.")
    void can_move_down_right() {
        final var sut = new Bishop(F4, Color.BLACK);

        final var result = sut.canMove(E5);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 그외 방향으로 이동할 수 없다.")
    void can_not_move_other_direction() {
        final var sut = new Bishop(F8, Color.BLACK);

        final var result = sut.canMove(F1);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비숍은 가는 경로에 기물이 없으면 참을 반환한다.")
    void true_if_bishop_piece_can_move() {
        final var pieceList = List.of(
                new Bishop(A1, BLACK),
                new Queen(F6, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                              .get();

        final var result = sut.canMove(E5, pieceList);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 가는 경로에 기물이 있으면 거짓을 반환한다.")
    void false_if_bishop_piece_can_move() {
        final var pieceList = List.of(
                new Bishop(A1, BLACK),
                new Bishop(C3, WHITE),
                new Queen(E5, WHITE));
        final var pieces = new Pieces(pieceList);

        final var sut = pieces.findPieceWithPoint(A1)
                             .get();

        final var result = sut.canMove(E5,pieceList);
        assertThat(result).isFalse();
    }

}
