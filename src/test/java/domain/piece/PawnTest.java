package domain.piece;

import static domain.piece.info.Color.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰 앞이 비어있을 때 앞으로 한 칸 이동할 수 있다")
    void isReachable() {
        final Piece whitePawn = new Pawn(WHITE);
        final Piece blackPawn = new Pawn(BLACK);

        assertThat(whitePawn.isReachable(Vector.of(0, 1), Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(Vector.of(0, -1), Empty.INSTANCE)).isTrue();
    }

    @Test
    @DisplayName("폰 앞에 같은편 말이 있을 때 앞으로 한 칸 이동할 수 없다")
    void defaultMovement() {
        final Piece whitePawn = new Pawn(WHITE);
        final Piece blackPawn = new Pawn(BLACK);

        assertThat(whitePawn.isReachable(Vector.of(0, 1), new Pawn(WHITE))).isFalse();
        assertThat(blackPawn.isReachable(Vector.of(0, -1), new Pawn(BLACK))).isFalse();
    }

    @Test
    void attackMovement() {
    }

    @Test
    void inverseIfBlack() {
    }
}
