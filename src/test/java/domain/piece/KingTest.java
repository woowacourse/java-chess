package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource(value = {"0, 0", "0, 1", "0, 2", "1, 0", "1, 2", "2, 0", "2, 1", "2, 2"})
    void 여덟_방향으로_움직일_수_있다(int targetRow, int targetColumn) {
        King king = new King(1, 1);

        boolean isMovable = king.isMovable(targetRow, targetColumn);

        assertThat(isMovable).isTrue();
    }
}
