package chess.domain.piece;

import chess.domain.square.Color;
import chess.domain.square.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static org.assertj.core.api.Assertions.assertThat;

class InitialPawnTest {
    @Test
    @DisplayName("처음 폰이 2칸을 이동할 수 있는지 확인한다.")
    void isMovable() {
        // when
        InitialPawn blackInitialPawn = new InitialPawn(Team.from(Color.BLACK), Role.INITIAL_PAWN);
        InitialPawn whiteInitialPawn = new InitialPawn(Team.from(Color.WHITE), Role.INITIAL_PAWN);

        // expected
        assertThat(blackInitialPawn.canMove(SOUTH, 2)).isTrue();
        assertThat(whiteInitialPawn.canMove(NORTH, 2)).isTrue();
    }

    @Test
    @DisplayName("처음 폰이 이동할 수 없는 위치인지 확인한다.")
    void canNotMove() {
        // when
        InitialPawn blackInitialPawn = new InitialPawn(Team.from(Color.BLACK), Role.INITIAL_PAWN);
        InitialPawn whiteInitialPawn = new InitialPawn(Team.from(Color.WHITE), Role.INITIAL_PAWN);

        // expected
        assertThat(blackInitialPawn.canMove(NORTH, 2)).isFalse();
        assertThat(whiteInitialPawn.canMove(SOUTH, 2)).isFalse();
    }
}
