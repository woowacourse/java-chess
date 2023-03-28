package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmptyTest {

    private static final Position A5 = new Position(A, FIVE);
    private static final Position B5 = new Position(B, FIVE);

    @Test
    @DisplayName("empty에서 computePath를 호출하면 예외가 발생한다")
    void emptyComputePathExceptionTest() {
        Empty empty = new Empty();

        assertThatThrownBy(() -> empty.computePathWithValidate(A5, B5))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("empty에서 canMove를 호출하면 예외가 발생한다")
    void emptyCanMoveExceptionTest() {
        Empty empty = new Empty();

        assertThatThrownBy(() -> empty.canMoveWithValidate(Map.of(new Position(A, TWO), true), A5, B5))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("empty는 흰색 점수가 0점이다.")
    @Test
    void getScore_whiteZero() {
        final var empty = new Empty();

        assertThat(empty.getScore(Color.WHITE)).isEqualTo(0);
    }

    @DisplayName("empty는 검정 점수가 0점이다.")
    @Test
    void getScore_blackZero() {
        final var empty = new Empty();

        assertThat(empty.getScore(Color.BLACK)).isEqualTo(0);
    }
}
