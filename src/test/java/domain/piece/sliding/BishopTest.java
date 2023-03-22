package domain.piece.sliding;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Color;
import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍은 기본 상태를 가진다")
    void propertyTest() {
        Piece bishop = new Bishop(Color.WHITE);

        assertThat(bishop.canJump()).isFalse();
        assertThat(bishop.isPawn()).isFalse();
        assertThat(bishop.isKing()).isFalse();
        assertThat(bishop.getColor()).isEqualTo(Color.WHITE);
        assertThat(bishop.getPoint()).isEqualTo(3);
    }

    @ParameterizedTest(name = "우측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableRightUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "좌측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableLeftUp(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "좌측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableLeftBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 7);
        Coordinate endCoordinate = new Coordinate(7 - distance, 7 - distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "우측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableRightBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "우측으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @ParameterizedTest(name = "좌측으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 7);
        Coordinate endCoordinate = new Coordinate(0, 7 - distance);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @ParameterizedTest(name = "상단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @ParameterizedTest(name = "하단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleDown(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, 0);
        Bishop bishop = new Bishop(Color.WHITE);

        assertThat(bishop.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }
}
