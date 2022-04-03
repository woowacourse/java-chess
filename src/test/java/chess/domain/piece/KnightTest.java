package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @Test
    @DisplayName("나이트는 2.5점이다.")
    void getPoint() {
        Piece knight = new Knight(Color.BLACK);
        assertThat(knight.getPoint()).isEqualTo(2.5);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"c2, true", "b5, true", "e2, true", "f5, true", "b3, true", "c6, true", "e6, true", "f3, true",
            "e5, false", "d3, false", "b4, false"})
    @DisplayName("나이트는 두 칸가고 한 칸 다른 방향으로 이동한 방향으로만 이동할 수 있다.")
    void canMove(String position, boolean expected) {
        Piece piece = new Knight(Color.BLACK);
        Position fromPosition = Position.of("d4");
        Position toPosition = Position.of(position);

        assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
    }
}