package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SquareTest {

    @DisplayName("Square를 생성한다.")
    @Test
    void createSquare() {
        assertDoesNotThrow(() -> Square.of(File.A, Rank.ONE));
    }

    @DisplayName("방향을 이용하여 다음 Rank를 반환한다.")
    @Test
    void nextRank() {
        Square square = Square.of(File.A, Rank.TWO);
        Square expectedSquare = Square.of(File.B, Rank.THREE);

        int fileDirection = 1;
        int rankDirection = -1;

        assertThat(square.next(fileDirection, rankDirection)).isEqualTo(expectedSquare);
    }
}
