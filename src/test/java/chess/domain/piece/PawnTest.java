package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private final Pawn white = new Pawn(Team.WHITE);
    private final Pawn black = new Pawn(Team.BLACK);

    @Test
    @DisplayName("앞으로 한칸 이동 가능한 지 판단하는 기능")
    void canMoveOneBlock() {
        assertThat(white.movable(new Position("a", "2"), new Position("a", "3"), Blank.getInstance()))
                .isTrue();
        assertThat(white.movable(new Position("a", "2"), new Position("a", "3"), new King(Team.WHITE)))
                .isFalse();

        assertThat(black.movable(new Position("a", "7"), new Position("a", "6"), Blank.getInstance()))
                .isTrue();
        assertThat(black.movable(new Position("a", "7"), new Position("a", "6"), new King(Team.WHITE)))
                .isFalse();
    }

    @Test
    @DisplayName("대각선 방향으로 한 칸 이동 가능한 지 판단하는 기능")
    void canMoveBlack() {
        assertThat(white.movable(new Position("a", "2"), new Position("b", "3"), new Pawn(Team.BLACK)))
                .isTrue();
        assertThat(white.movable(new Position("a", "2"), new Position("b", "3"), new Pawn(Team.WHITE)))
                .isFalse();

        assertThat(black.movable(new Position("a", "7"), new Position("b", "6"), new Pawn(Team.WHITE)))
                .isTrue();
        assertThat(black.movable(new Position("a", "7"), new Position("b", "6"), new Pawn(Team.BLACK)))
                .isFalse();
    }

    @Test
    @DisplayName("처음 움직이는 경우 두칸 이동 가능 판단 기능")
    void checkInitialMove() {
        assertThat(white.movable(new Position("a", "2"), new Position("a", "4"), Blank.getInstance()))
                .isTrue();
        assertThat(black.movable(new Position("a", "7"), new Position("a", "5"), Blank.getInstance()))
                .isTrue();
    }
}