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

    @ParameterizedTest(name = "목표 위치로 이동하기 위한 단위 벡터를 계산한다.")
    @CsvSource(value = {"4:8:0:1", "0:4:-1:0", "4:0:0:-1", "8:4:1:0", "8:8:1:1", "0:0:-1:-1", "8:0:1:-1", "0:8:-1:1"}, delimiter = ':')
    void computeUnitPosition(final int targetRank, final int targetFile, final int unitRank, final int unitFile) {
        // given
        final Position source = new Position(4, 4);
        final Position target = new Position(targetRank, targetFile);

        // when
        final Position unitPosition = source.computeUnitPosition(target);

        // then
        assertThat(unitPosition)
                .isEqualTo(new Position(unitRank, unitFile));
    }
}
