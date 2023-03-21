package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathTest {

    @Test
    @DisplayName("hasIntersection() : 두 Position 의 집합에서 교집합이 존재하는지 확인할 수 있다.")
    void test_hasIntersection() {

        //given
        final Set<Position> existedPositions1 = Set.of(
                new Position(1, 1),
                new Position(1, 2),
                new Position(1, 3)
        );

        final Set<Position> existedPositions2 = Set.of(
                new Position(1, 4),
                new Position(1, 5),
                new Position(1, 6)
        );

        final Path path = new Path(new Position(1, 1),
                                   new Position(2, 1));

        //when & then
        assertAll(
                () -> assertTrue(path.hasIntersection(existedPositions1)),
                () -> assertFalse(path.hasIntersection(existedPositions2))
        );


//        assertThatThrownBy(() -> path.hasIntersection(Set.of(position)))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessage("중간에 다른 기물이 존재합니다.");
    }
}
