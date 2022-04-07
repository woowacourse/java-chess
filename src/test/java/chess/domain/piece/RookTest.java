package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

    @Test
    @DisplayName("룩은 5점이다.")
    void getPoint() {
        Piece rook = new Rook(Color.BLACK);
        assertThat(rook.getPoint()).isEqualTo(5);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"d1, true", "d2, true", "d3, true", "e4, true", "c4, true", "d8,true",
            "e5, false", "g7, false"})
    @DisplayName("룩은 십자가 방향으로 이동할 수 있다.")
    void canMove(String position, boolean expected) {
        Piece piece = new Rook(Color.BLACK);
        Position fromPosition = Position.of("d4");
        Position toPosition = Position.of(position);

        assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
    }
}
