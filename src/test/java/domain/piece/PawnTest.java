package domain.piece;

import static domain.VectorFixture.DOWN;
import static domain.VectorFixture.DOWN_DOWN;
import static domain.VectorFixture.LEFT_UP;
import static domain.VectorFixture.UP;
import static domain.VectorFixture.UP_UP;
import static domain.piece.info.Color.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.piece.info.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰 앞이 비어있을 때 앞으로 한 칸 이동할 수 있다")
    void isReachable() {
        final Piece whitePawn = new Pawn(WHITE);
        final Piece blackPawn = new Pawn(BLACK);

        assertThat(whitePawn.isReachable(UP, Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(DOWN, Empty.INSTANCE)).isTrue();
    }

    @Test
    @DisplayName("폰 앞에 같은편 말이 있을 때 앞으로 한 칸 이동할 수 없다")
    void defaultMovement() {
        final Piece whitePawn = new Pawn(WHITE);
        final Piece blackPawn = new Pawn(BLACK);

        assertThat(whitePawn.isReachable(UP, new Pawn(WHITE))).isFalse();
        assertThat(blackPawn.isReachable(DOWN, new Pawn(BLACK))).isFalse();
    }

    @Test
    @DisplayName("처음 움직이는 폰은 앞으로 두 칸 움직일 수 있다")
    void initPawn() {
        final Piece whitePawn = new InitPawn(WHITE);
        final Piece blackPawn = new InitPawn(BLACK);

        assertThat(whitePawn.isReachable(UP_UP, Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(DOWN_DOWN, Empty.INSTANCE)).isTrue();
    }

    /*
        firstMove => moveTwo + no enemy
        ordinary => moveOne + no enemy
        opposite => diagonal + have enemy
     */


}
