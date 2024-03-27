package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("동일성을 올바르게 판단한다.")
    void equalsTest() {
        // given
        Position position = Position.of(File.A, Rank.ONE);
        // when
        boolean isSame = position == Position.of(File.A, Rank.ONE);
        // then
        assertThat(isSame).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,3,c,3", "a,4,h,4"})
    @DisplayName("두 위치가 수평 위치인 경우를 올바르게 판단한다.")
    void horizontalPositionTest(String sourceFileName, int sourceRankNumber,
                                String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnSameRank = source.isOnSameRank(destination);
        // then
        assertThat(isOnSameRank).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,3,a,6", "a,4,c,5"})
    @DisplayName("두 위치가 수평 위치가 아닌 경우를 올바르게 판단한다.")
    void invalidHorizontalPositionTest(String sourceFileName, int sourceRankNumber,
                                       String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnSameRank = source.isOnSameRank(destination);
        // then
        assertThat(isOnSameRank).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3,b,6", "a,1,a,8"})
    @DisplayName("두 위치가 수직 위치인 경우를 올바르게 판단한다.")
    void verticalPositionTest(String sourceFileName, int sourceRankNumber,
                              String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnSameFile = source.isOnSameFile(destination);
        // then
        assertThat(isOnSameFile).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3,c,6", "a,1,b,1"})
    @DisplayName("두 위치가 수직 위치가 아닌 경우를 올바르게 판단한다.")
    void invalidVerticalPositionTest(String sourceFileName, int sourceRankNumber,
                                     String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnSameFile = source.isOnSameFile(destination);
        // then
        assertThat(isOnSameFile).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,c,3", "h,8,a,1", "g,4,d,1"})
    @DisplayName("두 위치가 양의 기울기 대각선인 경우를 올바르게 판단한다.")
    void positiveSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                           String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnPositiveSlopeDiagonal = source.isOnPositiveSlopeDiagonal(destination);
        // then
        assertThat(isOnPositiveSlopeDiagonal).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,c,2", "a,8,h,1"})
    @DisplayName("두 위치가 양의 기울기 대각선이 아닌 경우를 올바르게 판단한다.")
    void invalidPositiveSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                                  String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnPositiveSlopeDiagonal = source.isOnPositiveSlopeDiagonal(destination);
        // then
        assertThat(isOnPositiveSlopeDiagonal).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"c,3,a,5", "a,8,h,1"})
    @DisplayName("두 위치가 음의 기울기 대각선인 경우를 올바르게 판단한다.")
    void negativeSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                           String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnNegativeSlopeDiagonal = source.isOnNegativeSlopeDiagonal(destination);
        // then
        assertThat(isOnNegativeSlopeDiagonal).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c,3,a,2", "a,1,h,8"})
    @DisplayName("두 위치가 음의 기울기 대각선이 아닌 경우를 올바르게 판단한다.")
    void invalidNegativeSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                                  String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnNegativeSlopeDiagonal = source.isOnNegativeSlopeDiagonal(destination);
        // then
        assertThat(isOnNegativeSlopeDiagonal).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"g,5", "f,6", "d,6", "c,5", "c,3", "d,2", "f,2", "g,3"})
    @DisplayName("두 위치가 나이트가 이동 가능한 경우를 올바르게 판단한다.")
    void knightPositionTest(String destinationFileName, int destinationFileNumber) {
        // given
        Position source = Position.of(File.E, Rank.FOUR);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnKnightRoute = source.isOnKnightRoute(destination);
        // then
        assertThat(isOnKnightRoute).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"e,6", "g,4", "b,4", "e,2", "a,1"})
    @DisplayName("두 위치가 나이트가 이동 불가능한 경우를 올바르게 판단한다.")
    void invalidKnightPositionTest(String destinationFileName, int destinationFileNumber) {
        // given
        Position source = Position.of(File.E, Rank.FOUR);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when
        boolean isOnKnightRoute = source.isOnKnightRoute(destination);
        // then
        assertThat(isOnKnightRoute).isFalse();
    }

    private Position createPositionByNameAndNumber(String fileName, int rankNumber) {
        return Position.of(File.from(fileName), Rank.from(rankNumber));
    }

    @Test
    @DisplayName("파일과 랭크의 차이를 받아 올바른 Position을 반환한다.")
    void createPositionByDifferencesOfTest() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        Position back = source.createPositionByDifferencesOf(1, 1);
        Position front = source.createPositionByDifferencesOf(-1, -1);
        // then
        assertThat(back).isEqualTo(Position.of(File.E, Rank.FOUR));
        assertThat(front).isEqualTo(Position.of(File.C, Rank.TWO));
    }

    @Test
    @DisplayName("현재 파일보다 높은 위치의 파일인지 계산한다")
    void hasLowerFileThan() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        boolean isLowerFile = source.hasLowerFileThan(Position.of(File.E, Rank.THREE));
        // then
        assertThat(isLowerFile).isTrue();
    }

    @Test
    @DisplayName("현재 파일보다 낮은 위치의 파일인지 계산한다")
    void hasHigherFileThan() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        boolean isHigherFile = source.hasHigherFileThan(Position.of(File.C, Rank.THREE));
        // then
        assertThat(isHigherFile).isTrue();
    }

    @Test
    @DisplayName("현재 랭크보다 높은 위치의 랭크인지 계산한다")
    void hasLowerRankThan() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        boolean isLowerRank = source.hasLowerRankThan(Position.of(File.D, Rank.FOUR));
        // then
        assertThat(isLowerRank).isTrue();
    }

    @Test
    @DisplayName("현재 랭크보다 낮은 위치의 랭크인지 계산한다")
    void hasHigherRankThan() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        boolean isHigherRank = source.hasHigherRankThan(Position.of(File.D, Rank.TWO));
        // then
        assertThat(isHigherRank).isTrue();
    }

    @Test
    @DisplayName("같은 포지션이 아닌지 체크한다")
    void isNotSamePosition() {
        // given
        Position source = Position.of(File.D, Rank.THREE);
        // when
        boolean isNotSamePosition = source.isNotEquals(Position.of(File.E, Rank.FOUR));
        // then
        assertThat(isNotSamePosition).isTrue();
    }
}
