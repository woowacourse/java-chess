package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.WHITE);
    }

    @Test
    @DisplayName("나이트 기물 생성")
    void createPawn() {
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("시작위치부터 도착위치까지의 이동경로 생성")
    void findRoute() {
        Position startPosition = new Position('b', '1');
        Position targetPosition = new Position('a', '3');
        List<Direction> expected = Collections.singletonList(Direction.UP_UP_LEFT);

        assertThat(knight.findRoute(startPosition, targetPosition)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2", "b,3", "b,4", "c,1", "c,2", "d,3", "c,5"})
    @DisplayName("기물이 갈 수 없는 지점 경로 확인 시 예외 발생")
    void exceptionFindRoute(char col, char row) {
        Position startPosition = new Position('b', '1');
        Position targetPosition = new Position(col, row);

        assertThatThrownBy(() -> knight.findRoute(startPosition, targetPosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물이 갈 수 없는 지점입니다.");
    }
}
