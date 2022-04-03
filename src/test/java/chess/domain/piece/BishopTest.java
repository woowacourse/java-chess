package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BishopTest {

    @Test
    @DisplayName("비숍은 3점이다.")
    void getPoint() {
        Piece bishop = new Bishop(Color.BLACK);
        assertThat(bishop.getPoint()).isEqualTo(3);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"c3, true", "h8, true", "g1, true", "d5, false"})
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다.")
    void canMove(String position, boolean expected) {
        Piece piece = new Bishop(Color.BLACK);
        Position fromPosition = Position.of("d4");
        Position toPosition = Position.of(position);

        assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
    }
}
