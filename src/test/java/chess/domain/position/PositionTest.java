package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PositionTest {
    /*
    ........  8
    ...O....  7
    ........  6
    ........  5
    O..O..O.  4
    ........  3
    ........  2
    ...O....  1
    abcdefgh
     */
    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 직선인지 확인할 수 있다")
    @Test
    void should_CheckStraightRelationShipWithPositions() {
        Position position = D4;

        assertAll(
                () -> assertThat(position.isStraightWith(A4)).isTrue(),
                () -> assertThat(position.isStraightWith(G4)).isTrue(),
                () -> assertThat(position.isStraightWith(D7)).isTrue(),
                () -> assertThat(position.isStraightWith(D1)).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    .x..x...  5
    ...O....  4
    ........  3
    .x..x...  2
    ........  1
    abcdefgh
     */
    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 직선이 아닌지 확인할 수 있다")
    @Test
    void should_ReturnFalse_When_NotStraightRelationShipWithPositions() {
        Position position = D4;

        assertAll(
                () -> assertThat(position.isStraightWith(E5)).isFalse(),
                () -> assertThat(position.isStraightWith(B5)).isFalse(),
                () -> assertThat(position.isStraightWith(B2)).isFalse(),
                () -> assertThat(position.isStraightWith(E2)).isFalse()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ..0.0...  5
    ...O....  4
    ..0.0...  3
    ........  2
    ........  1
    abcdefgh
     */
    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 대각인지 확인할 수 있다")
    @Test
    void should_ReturnTrue_When_DiagonalRelationShipWithPositions() {
        Position position = D4;

        assertAll(
                () -> assertThat(position.isDiagonalWith(E5)).isTrue(),
                () -> assertThat(position.isDiagonalWith(C5)).isTrue(),
                () -> assertThat(position.isDiagonalWith(E3)).isTrue(),
                () -> assertThat(position.isDiagonalWith(C3)).isTrue()
        );
    }

    /*
    ........  8
    ...x....  7
    ........  6
    .x......  5
    ...O..x.  4
    ........  3
    ....x...  2
    ........  1
    abcdefgh
     */
    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 대각이 아닌지 확인할 수 있다")
    @Test
    void should_ReturnFalse_When_NotDiagonalRelationShipWithPositions() {
        Position position = D4;

        assertAll(
                () -> assertThat(position.isDiagonalWith(D7)).isFalse(),
                () -> assertThat(position.isDiagonalWith(B5)).isFalse(),
                () -> assertThat(position.isDiagonalWith(G4)).isFalse(),
                () -> assertThat(position.isDiagonalWith(E2)).isFalse()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ...x....  3
    ........  2
    ....x...  1
    abcdefgh
    */
    @DisplayName("한 포지션에서 다른 포지션까지의 위치 차의 제곱을 계산할 수 있다")
    @Test
    void should_ReturnSquaredDistanceBetweenPositions() {
        Position position1 = D3;
        Position position2 = E1;

        assertThat(position1.squaredDistanceWith(position2)).isEqualTo(5);
    }

    /*
   ........  8
   ........  7
   ........  6
   ........  5
   ........  4
   x..x.x..  3
   ........  2
   ........  1
   abcdefgh
   */
    @DisplayName("포지션이 같은 행에 있는지 확인할 수 있다")
    @Test
    void should_CheckSameRowToTarget() {
        Position testPosition1 = D3;
        Position testPosition2 = A3;
        Position testPosition3 = F3;
        RowPosition rowPosition = new RowPosition(5);

        assertAll(
                () -> assertThat(testPosition1.isSameRowWith(rowPosition)),
                () -> assertThat(testPosition2.isSameRowWith(rowPosition)),
                () -> assertThat(testPosition3.isSameRowWith(rowPosition))
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ..0.....  5
    ...x....  4
    ....x...  3
    .....x..  2
    ......0.  1
    abcdefgh
    */
    @DisplayName("대각선 상의 포지션끼리 경유 경로를 구할 수 있다")
    @Test
    void should_ReturnDiagonalPassingPath() {
        Position start = C5;
        Position destination = G1;
        List<Position> expectedPath = List.of(D4, E3, F2);
        List<Position> actualPath = start.findPath(destination);

        assertThat(actualPath).containsExactlyElementsOf(expectedPath);
    }

    /*
    ........  8
    ........  7
    ........  6
    ..0xxxx0  5
    ........  4
    ........  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("직선 상의 포지션끼리 경유 경로를 구할 수 있다")
    @Test
    void should_ReturnStraightPassingPath() {
        Position start = C5;
        Position destination = H5;
        List<Position> expectedPath = List.of(D5, E5, F5, G5);
        List<Position> actualPath = start.findPath(destination);

        assertThat(actualPath).containsExactlyElementsOf(expectedPath);
    }
}
