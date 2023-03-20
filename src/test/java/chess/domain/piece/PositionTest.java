package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PositionTest {

    @Test
    @DisplayName("입력받은 위치를 복사한 새로운 위치를 반환한다.")
    void copy() {
        // given
        final Position position = new Position(0, 0);

        // when
        final Position copyPosition = Position.copy(position);

        // then
        assertThat(position)
                .isEqualTo(copyPosition);
    }

    @Test
    @DisplayName("이동 거리와 방향을 주었을 때 목표 지점의 위치를 반환한다.")
    void calculate() {
        // given
        final Position position = new Position(0, 0);

        // when
        final Position targetPosition = position.calculate(new Position(3, -3));

        // then
        assertThat(targetPosition)
                .isEqualTo(new Position(3, -3));
    }

    @ParameterizedTest(name = "위치가 입력받은 제한값을 넘어가는지 판단한다.")
    @CsvSource(value = {"-1:0:true", "0:-1:true", "0:0:false", "8:8:true", "9:9:true", "8:9:true", "9:8:true"}, delimiter = ':')
    void isOver(final int rank, final int file, final boolean expected) {
        // given
        final Position position = new Position(rank, file);

        // when, then
        assertThat(position.isOver())
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

    @Test
    @DisplayName("현재 위치와 입력받은 위치의 rank의 차이를 계산한다.")
    void calculateRankGap() {
        // given
        final Position source = new Position(4, 4);
        final Position target = new Position(5, 4);

        // when
        int gap = target.calculateRankGap(source);

        // then
        assertThat(gap)
                .isSameAs(1);
    }

    @Test
    @DisplayName("현재 위치와 입력받은 위치의 file의 차이를 계산한다.")
    void calculateFileGap() {
        // given
        final Position source = new Position(4, 4);
        final Position target = new Position(4, 5);

        // when
        int gap = target.calculateFileGap(source);

        // then
        assertThat(gap)
                .isSameAs(1);
    }

    @DisplayName("현재 위치의 rank과 입력받은 위치의 rank가 동일한지 판단한다.")
    @CsvSource(value = {"4:true", "5:false"}, delimiter = ':')
    void isRankSame(final int rank, final boolean expected) {
        // given
        final Position source = new Position(4, 4);

        // when, then
        assertThat(source.isRankSame(rank))
                .isSameAs(expected);
    }

    @DisplayName("현재 위치의 rank가 입력받은 위치의 rank보다 큰지 판단한다.")
    @CsvSource(value = {"5:true", "4:false", "3:false"}, delimiter = ':')
    void isRankGreaterThan(final int rank, final boolean expected) {
        // given
        final Position source = new Position(4, 4);
        final Position target = new Position(rank, 4);

        // when, then
        assertThat(source.isRankGreaterThan(target))
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "두 개의 위치가 동일한지 판단한다.")
    @CsvSource(value = {"4:true", "5:false"}, delimiter = ':')
    void isSame(final int rank, final boolean expected) {
        // given
        final Position source = new Position(4, 4);
        final Position target = new Position(rank, 4);

        // when
        boolean actual = source.isSame(target);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
