package chess.piece.type;

import chess.board.Location;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    @Test
    @DisplayName("초기 위치의 폰의 이동 반경 테스트")
    void canMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(7, 'a');

        Location moveTwiceForward= new Location(5, 'a');
        boolean moveTwiceForwardActual = pawn.canMove(now, moveTwiceForward);
        assertThat(moveTwiceForwardActual).isTrue();

        Location moveOnceForward = new Location(6, 'a');
        boolean moveOnceForwardActual = pawn.canMove(now, moveOnceForward);
        assertThat(moveOnceForwardActual).isTrue();

        Location moveDiagonal = new Location(6, 'b');
        boolean moveDiagonalAcutal = pawn.canMove(now, moveDiagonal);
        assertThat(moveDiagonalAcutal).isTrue();

        Location cantAfter = new Location(4, 'a');
        boolean cantActual = pawn.canMove(now, cantAfter);

        assertThat(cantActual).isFalse();
    }

    @Test
    @DisplayName("초기 위치가 아닌 일반적인 폰의 이동")
    void canMove2() {
        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(6, 'a');

        Location moveOnceForward = new Location(5, 'a');
        boolean moveOnceForwardActual = pawn.canMove(now, moveOnceForward);
        assertThat(moveOnceForwardActual).isTrue();

        Location moveDiagonal = new Location(5, 'b');
        boolean moveDiagonalAcutal = pawn.canMove(now, moveDiagonal);
        assertThat(moveDiagonalAcutal).isTrue();

        Location moveTwiceForward= new Location(4, 'a');
        boolean moveTwiceForwardActual = pawn.canMove(now, moveTwiceForward);
        assertThat(moveTwiceForwardActual).isFalse();
    }

}