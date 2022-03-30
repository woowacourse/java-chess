package chess.domain.refactorBoard;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.refactorPosition.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "a,2,a,2,UP,0", "a,2,a,3,UP,1", "a,2,a,4,UP,2",
            "a,7,a,7,DOWN,0", "a,7,a,6,DOWN,1", "a,7,a,5,DOWN,2",
            "a,3,a,3,RIGHT,0", "a,3,h,3,RIGHT,7",
            "h,3,h,3,LEFT,0", "h,3,a,3,LEFT,7",
            "a,2,b,3,UP_RIGHT,1", "a,2,f,7,UP_RIGHT,5",
            "f,2,e,3,UP_LEFT,1", "f,2,a,7,UP_LEFT,5",
            "a,7,b,6,DOWN_RIGHT,1", "a,7,f,2,DOWN_RIGHT,5",
            "f,7,e,6,DOWN_LEFT,1", "f,7,a,2,DOWN_LEFT,5"})
    @DisplayName("기물의 현재 위치에서 최종 위치로 이동할 때 이동하는 모든 위치 값들을 구할 수 있다.")
    void findByRoute(String fromColumn, String fromRow, String toColumn, String toRow, Direction direction, int size) {
        final List<Position> directions = direction.route(
                Position.of(fromColumn, fromRow), Position.of(toColumn, toRow));

        assertThat(directions).hasSize(size);
    }
}
