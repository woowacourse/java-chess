package chess.piece;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"1:1", "1:-1", "-1:1", "-1:-1"}, delimiter = ':')
    @DisplayName("bishop 기물 이동 위치 검증 - true")
    void checkPositionWhenTrue(int a, int b) {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "1:2", "2:-1", "-1:0"}, delimiter = ':')
    @DisplayName("bishop 기물 이동 위치 검증 - false")
    void checkPositionWhenFalse(int a, int b) {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }

    @Test
    @DisplayName("source와 target 사이에 비숍이 이동가능한 위치 리스트 반환")
    void checkAllPositionOfPossible() {
        Bishop bishop = new Bishop(Color.WHITE);
        assertThat(bishop.computeBetweenTwoPositionByLine(new Position(7, 2),
                new Position(5, 4))).isEqualTo(List.of(new Position(6, 3)));
    }
}
