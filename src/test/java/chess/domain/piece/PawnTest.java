package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("화이트 폰은 rank가 높아지는 방향으로 한 칸 이동할 수 있다.")
    @Test
    void testWhitePawnMoveTrue() {
        Piece piece = new Pawn(Color.WHITE);
        Position src = Position.of("a2");
        Position dest = Position.of("a3");

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    @DisplayName("화이트 폰은 rank가 낮아지는 방향으로 이동할 수 있다.")
    @Test
    void testWhitePawnMoveFalse() {
        Piece piece = new Pawn(Color.WHITE);
        Position src = Position.of("a3");
        Position dest = Position.of("a2");

        assertThat(piece.canMove(src, dest)).isFalse();
    }

    @DisplayName("화이트폰은 첫 번째 이동일 때 rank가 높아지는 방향으로 두 칸 이동할 수 있다")
    @Test
    void testWhitePawnMoveTwiceFirst() {
        Piece piece = new Pawn(Color.WHITE);
        Position src = Position.of("a2");
        Position dest = Position.of("a4");

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동이 아닌데 두 칸 움직이는 경우 false를 반환한다")
    @Test
    void testThree() {
        Piece piece = new Pawn(Color.WHITE);
        Position src = Position.of("a3");
        Position dest = Position.of("a5");

        assertThat(piece.canMove(src, dest)).isFalse();
    }

    @DisplayName("블랙 폰은 rank가 낮아지는 방향으로 한 칸 이동할 수 있다.")
    @Test
    void testBlackPawnMoveTrue() {
        Piece piece = new Pawn(Color.BLACK);
        Position src = Position.of("a7");
        Position dest = Position.of("a6");

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    @DisplayName("블랙 폰은 첫 번째 이동일 때 rank가 높아지는 방향으로 두 칸 이동할 수 있다")
    @Test
    void testBlackPawnMoveDoubleTrue() {
        Piece piece = new Pawn(Color.BLACK);
        Position src = Position.of("a7");
        Position dest = Position.of("a5");

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동이 아닌데 두 칸 움직이는 경우 false를 반환한다")
    @Test
    void testBlackPawnMoveDoubleFalse() {
        Piece piece = new Pawn(Color.BLACK);
        Position src = Position.of("a6");
        Position dest = Position.of("a4");

        assertThat(piece.canMove(src, dest)).isFalse();
    }

    @DisplayName("블랙 폰은 rank가 높아지는 방향으로 이동할 수 있다.")
    @Test
    void testBlackPawnMoveFalse() {
        Piece piece = new Pawn(Color.BLACK);
        Position src = Position.of("a6");
        Position dest = Position.of("a7");

        assertThat(piece.canMove(src, dest)).isFalse();
    }
}
