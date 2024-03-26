package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionJudgeTest {
    @DisplayName("목적지가 수직 위에 있다면 움직이는 방향은 N이다")
    @Test
    void should_DirectionIsN_When_DestinationIsStarightHigherThanStart() {
        Position start = Position.of(4, 1);
        Position destination = Position.of(1, 1);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.UP);
    }

    @DisplayName("목적지가 수직 아래에 있다면 방향은 S이다")
    @Test
    void should_DirectionIsS_When_DestinationIsStraightAndLowerThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(4, 1);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.DOWN);
    }

    @DisplayName("목적지가 수평 오른쪽에 있다면 방향은 E이다")
    @Test
    void should_DirectionIsE_When_DestinationIsStraightAndRightThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(1, 4);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.RIGHT);
    }

    @DisplayName("목적지가 수평 왼쪽에 있다면 방향은 W이다")
    @Test
    void should_DirectionIsW_When_DestinationIsStraightAndLeftThanStart() {
        Position start = Position.of(1, 4);
        Position destination = Position.of(1, 1);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.LEFT);
    }

    @DisplayName("목적지가 대각선 오른쪽 위에 있다면 방향은 NE이다")
    @Test
    void should_DirectionIsNE_When_DestinationIsDiagnal_And_Right_And_HigherThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(0, 2);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.UP_RIGHT);
    }

    @DisplayName("목적지가 대각선 왼쪽 위에 있다면 방향은 NW이다")
    @Test
    void should_DirectionIsNE_When_DestinationIsDiagnal_And_Left_And_HigherThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(0, 0);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.UP_LEFT);
    }

    @DisplayName("목적지가 대각선 오른쪽 아래에 있다면 방향은 SE이다")
    @Test
    void should_DirectionIsNE_When_DestinationIsDiagnal_And_Right_And_LowerThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(2, 2);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.DOWN_RIGHT);
    }

    @DisplayName("목적지가 대각선 왼쪽 위에 있다면 방향은 SW이다")
    @Test
    void should_DirectionIsNE_When_DestinationIsDiagnal_And_Left_And_LowerThanStart() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(2, 0);

        assertThat(DirectionJudge.judge(start, destination)).isEqualTo(Direction.DOWN_LEFT);
    }

    @DisplayName("목적지가 8방향 중 아무 곳에도 속하지 않으면 오류를 발생시킨다")
    @Test
    void should_ThrowIllegalStateException_When_DestinationIsNotContatinsInDirections() {
        Position start = Position.of(1, 1);
        Position destination = Position.of(0, 3);

        assertThatThrownBy(() -> DirectionJudge.judge(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 위치의 방향을 특정할 수 없습니다.");
    }
}


