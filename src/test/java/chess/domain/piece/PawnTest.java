package chess.domain.piece;

import chess.domain.position.Position;
import chess.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private final Pawn white = new Pawn(false);
    private final Pawn black = new Pawn(true);

    @Test
    @DisplayName("앞으로 한칸 이동 가능한 지 판단하는 기능")
    void canMoveOneBlock() {
        assertThat(white.canMove(new Position("a", "2"), new Position("a", "3"), new Blank()))
                .isTrue();
        assertThat(white.canMove(new Position("a", "2"), new Position("a", "3"), new King(false)))
                .isFalse();

        assertThat(black.canMove(new Position("a", "7"), new Position("a", "6"), new Blank()))
                .isTrue();
        assertThat(black.canMove(new Position("a", "7"), new Position("a", "6"), new King(false)))
                .isFalse();
    }

    @Test
    @DisplayName("대각선 방향으로 한 칸 이동 가능한 지 판단하는 기능")
    void canMoveBlack() {
        assertThat(white.canMove(new Position("a", "2"), new Position("b", "3"), new Pawn(true)))
                .isTrue();
        assertThat(white.canMove(new Position("a", "2"), new Position("b", "3"), new Pawn(false)))
                .isFalse();

        assertThat(black.canMove(new Position("a", "7"), new Position("b", "6"), new Pawn(false)))
                .isTrue();
        assertThat(black.canMove(new Position("a", "7"), new Position("b", "6"), new Pawn(true)))
                .isFalse();
    }

    @Test
    @DisplayName("처음 움직이는 경우 두칸 이동 가능 판단 기능")
    void checkInitialMove() {
        assertThat(white.canMove(new Position("a", "2"), new Position("a", "4"), new Blank()))
                .isTrue();
        assertThat(black.canMove(new Position("a", "7"), new Position("a", "5"), new Blank()))
                .isTrue();
    }
}