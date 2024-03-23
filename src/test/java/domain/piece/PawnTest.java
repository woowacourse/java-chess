package domain.piece;

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
        final Piece whitePawn = new Pawn(Color.WHITE);
        final Piece blackPawn = new Pawn(Color.BLACK);

        assertThat(whitePawn.isReachable(Vector.of(0, 1), Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(Vector.of(0, -1), Empty.INSTANCE)).isTrue();
    }

    @Test
    @DisplayName("폰은 앞으로 한 칸 이동할 수 있다")
    void defaultMovement() {
    }

    @Test
    void attackMovement() {
    }

    @Test
    void inverseIfBlack() {
    }
}
