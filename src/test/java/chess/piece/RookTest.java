package chess.piece;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"1:0", "-1:0", "0:1", "0:-2"}, delimiter = ':')
    @DisplayName("rook 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(int a, int b) {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "1:2", "2:-1", "-1:-3"}, delimiter = ':')
    @DisplayName("rook 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(int a, int b) {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }

    @Test
    @DisplayName("source와 target 사이에 룩이 이동가능한 위치 리스트 반환")
    void checkAllPositionOfPossible() {
        Rook rook = new Rook(Color.WHITE);
        assertThat(rook.computeBetweenTwoPosition(new Position(0, 0), new Position(0, 3)))
                .isEqualTo(List.of(new Position(0, 1), new Position(0, 2)));
    }
}
