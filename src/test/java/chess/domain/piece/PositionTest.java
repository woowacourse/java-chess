package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PositionTest {

    @Test
    @DisplayName("이동 거리와 방향을 주었을 때 목표 지점의 위치를 반환한다.")
    void calculate() {
        // given
        final Position position = new Position(0, 0);

        // when
        final Position targetPosition = position.calculate(3, -3);

        // then
        assertThat(targetPosition)
                .isEqualTo(new Position(3, -3));
    }

    @ParameterizedTest(name = "위치가 입력받은 제한값을 넘어가는지 판단한다.")
    @CsvSource(value = {"-1:0:true", "0:-1:true", "0:0:false", "8:8:true", "9:9:true", "8:9:true", "9:8:true"}, delimiter = ':')
    void isOver(final int rank, final int file, final boolean expected) {
        // given
        final Position position = new Position(rank, file);
        final int limit = 8;

        // when, then
        assertThat(position.isOver(limit))
                .isSameAs(expected);
    }
}
