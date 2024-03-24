package domain.piece;

import static domain.VectorFixture.DOWN_DOWN;
import static domain.VectorFixture.UP_UP;
import static domain.board.Color.BLACK;
import static domain.board.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitPawnTest {
    @Test
    @DisplayName("처음 움직이는 폰은 앞으로 두 칸 움직일 수 있다")
    void initPawn() {
        final Piece whitePawn = new InitPawn(WHITE);
        final Piece blackPawn = new InitPawn(BLACK);

        assertThat(whitePawn.isReachable(UP_UP, Empty.INSTANCE)).isTrue();
        assertThat(blackPawn.isReachable(DOWN_DOWN, Empty.INSTANCE)).isTrue();
    }

}
