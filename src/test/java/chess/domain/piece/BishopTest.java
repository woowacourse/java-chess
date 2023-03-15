package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @ParameterizedTest
    @CsvSource({"2,2,true", "1,2,false"})
    void canMove(int fileInterval, int rankInterval, boolean canMove) {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.canMove(fileInterval, rankInterval)).isEqualTo(canMove);
    }
}
