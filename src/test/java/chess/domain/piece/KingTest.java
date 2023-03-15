package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource({"1,1,true", "1,2,false"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        King king = new King(Color.BLACK);
        assertThat(king.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }
}
