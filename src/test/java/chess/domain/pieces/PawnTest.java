package chess.domain.pieces;

import chess.domain.position.Col;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("Pawn이 Black 팀으로 생성되면, row의 실제 좌표 위치는 1이다.")
    void blackTeamPositionCheck() {
        for (char alpha = 'a'; alpha <= 'h'; ++alpha) {
            String col = Character.toString(alpha);
            Pawn pawn = Pawn.black(Col.location(col));

            Position pawnPosition = pawn.position();
            assertThat(pawnPosition.row()).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("Pawn이 White 팀으로 생성되면, row의 실제 위치는 6이다.")
    void whiteTeamPositionCheck() {
        for (char alpha = 'a'; alpha <= 'h'; ++alpha) {
            String col = Character.toString(alpha);
            Pawn pawn = Pawn.white(Col.location(col));

            Position pawnPosition = pawn.position();
            assertThat(pawnPosition.row()).isEqualTo(6);
        }
    }

    @Test
    @DisplayName("Pawn이 Black 팀으로 생성되면, initial은 P이다.")
    void blackTeamInitialCheck() {
        Pawn pawn = Pawn.black(1);
        assertThat(pawn.initial()).isEqualTo("P");
    }

    @Test
    @DisplayName("Pawn이 White 팀으로 생성되면, initial은 P이다.")
    void whiteTeamInitialCheck() {
        Pawn pawn = Pawn.white(1);
        assertThat(pawn.initial()).isEqualTo("P");
    }
}