package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyTest {

    private static final Position A5 = new Position(A, FIVE);
    private static final Position B5 = new Position(B, FIVE);

    @Test
    @DisplayName("empty에서 computePath를 호출하면 예외가 발생한다")
    void emptyComputePathExceptionTest() {
        Empty empty = new Empty();

        assertThatThrownBy(() -> empty.computePath(A5, B5))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("empty에서 canMove를 호출하면 예외가 발생한다")
    void emptyCanMoveExceptionTest() {
        Empty empty = new Empty();

        assertThatThrownBy(() -> empty.canMove(Map.of(new Position(A, TWO), true), A5, B5))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
