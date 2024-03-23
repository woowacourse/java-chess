package domain.piece;

import static domain.VectorFixture.DOWN;
import static domain.VectorFixture.DOWN_DOWN;
import static domain.VectorFixture.DOWN_LEFT;
import static domain.VectorFixture.LEFT;
import static domain.VectorFixture.LEFT_UP;
import static domain.VectorFixture.RIGHT;
import static domain.VectorFixture.RIGHT_DOWN;
import static domain.VectorFixture.UP;
import static domain.VectorFixture.UP_RIGHT;
import static domain.VectorFixture.UP_UP;
import static domain.piece.info.Color.*;
import static org.assertj.core.api.AbstractSoftAssertions.assertAll;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.piece.info.Vector;
import org.junit.jupiter.api.Assertions;
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
    @DisplayName("폰은 뒤, 옆으로 움직일 수 없다")
    void pawnBackward() {
        final Piece whitePawn = new InitPawn(WHITE);
        final Piece blackPawn = new InitPawn(BLACK);

        Assertions.assertAll(
                () -> assertThat(whitePawn.isReachable(LEFT, Empty.INSTANCE)).isFalse(),
                () -> assertThat(whitePawn.isReachable(RIGHT, Empty.INSTANCE)).isFalse(),
                () -> assertThat(whitePawn.isReachable(DOWN, Empty.INSTANCE)).isFalse(),
                () -> assertThat(whitePawn.isReachable(DOWN_LEFT, Empty.INSTANCE)).isFalse(),
                () -> assertThat(whitePawn.isReachable(RIGHT_DOWN, Empty.INSTANCE)).isFalse(),
                () -> assertThat(blackPawn.isReachable(UP, Empty.INSTANCE)).isFalse(),
                () -> assertThat(blackPawn.isReachable(UP_RIGHT, Empty.INSTANCE)).isFalse(),
                () -> assertThat(blackPawn.isReachable(LEFT_UP, Empty.INSTANCE)).isFalse()
        );


    }

    @Test
    @DisplayName("처음 움직이는 폰은 앞으로 두 칸 움직일 수 있다")
    void initPawn() {
        final Piece whitePawn = new InitPawn(WHITE);
        final Piece blackPawn = new InitPawn(BLACK);

        assertThat(whitePawn.isReachable(UP_UP, Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(DOWN_DOWN, Empty.INSTANCE)).isTrue();
    }

    @Test
    @DisplayName("이미 움직인 폰은 앞으로 두 칸 움직일 수 없다")
    void movedPawn() {
        final Piece whitePawn = new Pawn(WHITE);
        final Piece blackPawn = new Pawn(BLACK);

        assertThat(whitePawn.isReachable(UP_UP, Empty.INSTANCE)).isFalse();
        assertThat(blackPawn.isReachable(DOWN_DOWN, Empty.INSTANCE)).isFalse();
    }


    /*
        firstMove => moveTwo + no enemy
        ordinary => moveOne + no enemy
        opposite => diagonal + have enemy
     */


}
