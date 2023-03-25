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

class QueenTest {

    @Test
    @DisplayName("퀸은 기본 상태를 가진다")
    void propertyTest() {
        Piece queen = new Queen(Color.WHITE);

        assertThat(queen.canJump()).isFalse();
        assertThat(queen.isPawn()).isFalse();
        assertThat(queen.isKing()).isFalse();
        assertThat(queen.getColor() == Color.WHITE).isTrue();
        assertThat(queen.getPoint()).isEqualTo(9);
    }

    @ParameterizedTest(name = "우측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableRightUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "좌측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableLeftUp(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "좌측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableLeftBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 7);
        Coordinate endCoordinate = new Coordinate(7 - distance, 7 - distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "우측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableRightBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "왼쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 7);
        Coordinate endCoordinate = new Coordinate(0, 7 - distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "오른쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "위쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "아래쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isPieceMovableDown(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, 0);
        Queen queen = new Queen(Color.WHITE);

        assertThat(queen.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }
}
