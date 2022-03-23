package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    private final Position currentPosition = new Position(3, 'c');
    private final Bishop bishop = new Bishop(currentPosition);

    @ParameterizedTest(name = "{2} 방향으로 이동")
    @DisplayName("대각선 중 한 방향으로 칸수 제한없이 이동 가능하다.")
    @CsvSource({"5, 'a', \"북서\"", "5, 'e', \"북동\"", "1, 'a', \"남서\"", "1, 'e', \"남동\""})
    void move(final int rank, final char file, final String direction) {
        final Position expected = new Position(rank, file);

        final Position actual = bishop.move(currentPosition, expected);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("대각선 방향이 아닌 이동일 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextLinearPosition = new Position(3, 'f');

        assertThatThrownBy(() -> bishop.move(currentPosition, nextLinearPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선이 아닌 방향으로 이동했습니다.");
    }

    @Test
    @DisplayName("움직이지 않은 경우, 예외를 발생시킨다.")
    void notMoveException() {
        assertThatThrownBy(() -> bishop.move(currentPosition, currentPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍은 1칸 이상 이동해야 합니다.");
    }
}
