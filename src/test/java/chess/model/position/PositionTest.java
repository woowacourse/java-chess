package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Position position = Position.of(File.A, Rank.ONE);
        Position otherPosition = Position.of(File.A, Rank.ONE);

        // when
        boolean result = position.equals(otherPosition);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("타겟 위치와 소스 위치 간의 이동량을 계산한다.")
    void calculateMovement() {
        // given
        Position sourcePosition = Position.of(File.A, Rank.FOUR);
        Position targetPosition = Position.of(File.B, Rank.TWO);

        // when
        Movement movement = targetPosition.calculateMovement(sourcePosition);

        // then
        Difference fileDifference = Difference.from(1);
        Difference rankDifference = Difference.from(-2);
        assertThat(movement).isEqualTo(new Movement(fileDifference, rankDifference));
    }

    @Test
    @DisplayName("오프셋으로 다음 위치를 계산한다.")
    void calculateNextPosition() {
        // given
        Position position = Position.of(File.A, Rank.FOUR);
        int fileOffset = 2;
        int rankOffset = -1;

        // when
        Position nextPosition = position.calculateNextPosition(fileOffset, rankOffset);

        // then
        assertThat(nextPosition).isSameAs(Position.of(File.C, Rank.THREE));
    }

}
