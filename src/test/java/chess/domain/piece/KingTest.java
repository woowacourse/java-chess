package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

    @Test
    @DisplayName("킹은 0점이다.")
    void getPoint() {
        Piece king = new King(Color.BLACK);
        assertThat(king.getPoint()).isZero();
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"c3, true", "c4, true", "c5, true", "d3, true", "d5, true", "e3, true", "e4, true", "e5, true",
            "d6, false", "d2, false", "f6, false", "e2, false"})
    @DisplayName("킹은 주변 8방향으로 이동할 수 있다.")
    void canMove(String position, boolean expected) {
        Piece piece = new King(Color.BLACK);
        Position fromPosition = Position.of("d4");
        Position toPosition = Position.of(position);

        assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
    }
}
