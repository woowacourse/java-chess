package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    @DisplayName("현재 위치에서 정 동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueEastDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("h2");

        assertThat(Direction.isMovableToEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseEastDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("h3");

        assertThat(Direction.isMovableToWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueWestDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("c2");

        assertThat(Direction.isMovableToWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseWestDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("c3");

        assertThat(Direction.isMovableToWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 남쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f1");

        assertThat(Direction.isMovableToSouth(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 남쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("g1");

        assertThat(Direction.isMovableToSouth(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 북쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f4");

        assertThat(Direction.isMovableNorth(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 북쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("g4");

        assertThat(Direction.isMovableNorth(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 북동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthEastDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f5");

        assertThat(Direction.isMovableNorthEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 북동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthEastDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f6");

        assertThat(Direction.isMovableNorthEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 북서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthWestDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b5");

        assertThat(Direction.isMovableNorthWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 북서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthWestDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b6");

        assertThat(Direction.isMovableNorthWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 남동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthEastDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f1");

        assertThat(Direction.isMovableSouthEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 남동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthEastDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f3");

        assertThat(Direction.isMovableSouthEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 정 남서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthWestDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b1");

        assertThat(Direction.isMovableSouthWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 정 남서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthWestDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b3");

        assertThat(Direction.isMovableSouthWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 북북동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthNorthEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("d5");

        assertThat(Direction.isMovableNorthNorthEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 북북동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthNorthEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("d6");

        assertThat(Direction.isMovableNorthNorthEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 북북서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthNorthWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("b5");

        assertThat(Direction.isMovableNorthNorthWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 북북서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthNorthWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a5");

        assertThat(Direction.isMovableNorthNorthWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 북동동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthEastEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("e4");

        assertThat(Direction.isMovableNorthEastEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 북동동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthEastEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("e5");

        assertThat(Direction.isMovableNorthEastEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 북서서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueNorthWestWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a4");

        assertThat(Direction.isMovableNorthWestWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 북서서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseNorthWestWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a5");

        assertThat(Direction.isMovableNorthWestWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 남남동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthSouthEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("d1");

        assertThat(Direction.isMovableSouthSouthEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 남남동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthSouthEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("d2");

        assertThat(Direction.isMovableSouthSouthEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 남남서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthSouthWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("b1");

        assertThat(Direction.isMovableSouthSouthWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 남남서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthSouthWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a1");

        assertThat(Direction.isMovableSouthSouthWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 남동동쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthEastEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("e2");

        assertThat(Direction.isMovableSouthEastEast(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 남동동쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthEastEastDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("e3");

        assertThat(Direction.isMovableSouthEastEast(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 남서서쪽에 목표 위치가 존재할 때 true를 리턴한다")
    void shouldSucceedToCheckTrueSouthWestWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a2");

        assertThat(Direction.isMovableSouthWestWest(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 남서서쪽에 목표 위치가 존재하지 않을 때 false를 리턴한다")
    void shouldSucceedToCheckFalseSouthWestWestDirection() {
        Position sourcePosition = Position.findPosition("c3");
        Position targetPosition = Position.findPosition("a1");

        assertThat(Direction.isMovableSouthWestWest(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    @DisplayName("Direction이 Rook의 움직임에 해당되면 true를 리턴한다.")
    void shouldSucceedToCheckRookDirectionTrue() {
        Direction rookDirection = Direction.EAST;

        assertThat(Direction.isRookDirection(rookDirection)).isTrue();
    }
}