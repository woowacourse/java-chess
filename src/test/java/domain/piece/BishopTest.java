package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @ParameterizedTest
    @CsvSource(value = {"0, 0", "0, 2", "2, 0", "2, 2"})
    void 대각선_방향으로_움직일_수_있다(int targetRow, int targetColumn) {
        Bishop bishop = new Bishop(1, 1);

        boolean isMovable = bishop.isMovable(targetRow, targetColumn);

        assertThat(isMovable).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "5, 5", "2, 6", "6, 2"})
    void 대각선_방향으로_몇_칸이든_움직일_수_있다(int targetRow, int targetColumn) {
        Bishop bishop = new Bishop(4, 4);

        boolean isMovable = bishop.isMovable(targetRow, targetColumn);

        assertThat(isMovable).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 0", "0, 1", "2, 1", "1, 2"})
    void 직선_방향으로_움직일_수_없다(int targetRow, int targetColumn) {
        Bishop bishop = new Bishop(1, 1);

        boolean isMovable = bishop.isMovable(targetRow, targetColumn);

        assertThat(isMovable).isFalse();
    }
}
