package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    @ParameterizedTest
    @CsvSource({"1,1,true", "1,2,false", "3,0,true"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }
}
