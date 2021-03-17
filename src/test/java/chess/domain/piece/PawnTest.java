package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("타겟 위치로 이동 가능한 지 판단하는 기능")
    void canMove() {
        Pawn pawn = new Pawn(false);
        assertThat(pawn.canMove(new Position("a", "2"), new Position("a", "3")))
                .isTrue();
    }
}