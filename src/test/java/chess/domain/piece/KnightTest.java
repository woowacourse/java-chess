package chess.domain.piece;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource({"2,1,true", "1,1,false"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        Knight knight = new Knight(Color.BLACK);
        assertThat(knight.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }

}
