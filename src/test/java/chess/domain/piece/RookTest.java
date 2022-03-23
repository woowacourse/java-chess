package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    private Rook rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.WHITE, new Position('b', '2'));
    }

    @Test
    @DisplayName("룩 기물 생성")
    void createRook() {
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,3", "a,1", "c,1", "c,3"})
    @DisplayName("룩 위치 이동 불가 예외발생")
    void moveException(char row, char col) {
        Position movePosition = new Position(row, col);

        assertThatThrownBy(() -> rook.move(movePosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("룩은 상하좌우 방향으로만 움직일 수 있습니다.");
    }
}
