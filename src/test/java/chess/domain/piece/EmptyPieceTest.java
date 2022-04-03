package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmptyPieceTest {

    @Test
    @DisplayName("빈 말은 0점이다.")
    void getPoint() {
        Piece emptyPiece = EmptyPiece.getInstance();
        assertThat(emptyPiece.getPoint()).isZero();
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @ValueSource(strings = {"c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5"})
    @DisplayName("빈 말은 어디로든 갈 수 없다.")
    void canMove(String toPosition) {
        Piece emptyPiece = EmptyPiece.getInstance();
        assertThat(emptyPiece.canMove(Position.of("d4"), Position.of(toPosition))).isFalse();
    }

    @Test
    @DisplayName("빈 말은 갈 수 있는 방향을 구할 수 없습니다.")
    void getDirections() {
        Piece emptyPiece = EmptyPiece.getInstance();
        assertThatThrownBy(emptyPiece::getMovableDirections)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("빈 피스는 갈 수 있는 방향이 없습니다.");
    }
}
