package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

    @Test
    @DisplayName("퀸은 9점이다.")
    void getPoint() {
        Piece queen = new Queen(Color.BLACK);
        assertThat(queen.getPoint()).isEqualTo(9);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"c3, true", "c4, true", "g1, true", "d1, true", "d2, true", "d3, true", "e4, true", "h8, true",
            "d8, true", "e6, false", "e2, false"})
    @DisplayName("퀸은 8방향으로 쭉 이동할 수 있다.")
    void canMove(String position, boolean expected) {
        Piece piece = new Queen(Color.BLACK);
        Position fromPosition = Position.of("d4");
        Position toPosition = Position.of(position);

        assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
    }
}
