package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest
    @CsvSource({"1,0,true", "1,1,true", "3,0,false"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }
}
