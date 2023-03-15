package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {
    @ParameterizedTest
    @CsvSource({"3,0,true", "1,2,false"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }
}
