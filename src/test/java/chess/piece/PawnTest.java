package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.piece.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
    @Test
    void pawnCreateTest() {
        Pawn pawn = new Pawn(BLACK);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }

}
