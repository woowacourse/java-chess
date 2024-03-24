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
        // when, then
        assertThat(source.isOnSameRank(destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,3,a,6", "a,4,c,5"})
    @DisplayName("두 위치가 수평 위치가 아닌 경우를 올바르게 판단한다.")
    void invalidHorizontalPositionTest(String sourceFileName, int sourceRankNumber,
                                       String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnSameRank(destination)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3,b,6", "a,1,a,8"})
    @DisplayName("두 위치가 수직 위치인 경우를 올바르게 판단한다.")
    void verticalPositionTest(String sourceFileName, int sourceRankNumber,
                              String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnSameFile(destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3,c,6", "a,1,b,1"})
    @DisplayName("두 위치가 수직 위치가 아닌 경우를 올바르게 판단한다.")
    void invalidVerticalPositionTest(String sourceFileName, int sourceRankNumber,
                                     String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnSameFile(destination)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,c,3", "h,8,a,1", "g,4,d,1"})
    @DisplayName("두 위치가 양의 기울기 대각선인 경우를 올바르게 판단한다.")
    void positiveSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                           String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnPositiveSlopeDiagonal(destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,c,2", "a,8,h,1"})
    @DisplayName("두 위치가 양의 기울기 대각선이 아닌 경우를 올바르게 판단한다.")
    void invalidPositiveSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                                  String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnPositiveSlopeDiagonal(destination)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"c,3,a,5", "a,8,h,1"})
    @DisplayName("두 위치가 음의 기울기 대각선인 경우를 올바르게 판단한다.")
    void negativeSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                           String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnNegativeSlopeDiagonal(destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c,3,a,2", "a,1,h,8"})
    @DisplayName("두 위치가 음의 기울기 대각선이 아닌 경우를 올바르게 판단한다.")
    void invalidNegativeSlopeDiagonalPositionTest(String sourceFileName, int sourceRankNumber,
                                                  String destinationFileName, int destinationFileNumber) {
        // given
        Position source = createPositionByNameAndNumber(sourceFileName, sourceRankNumber);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnNegativeSlopeDiagonal(destination)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"g,5", "f,6", "d,6", "c,5", "c,3", "d,2", "f,2", "g,3"})
    @DisplayName("두 위치가 나이트가 이동 가능한 경우를 올바르게 판단한다.")
    void knightPositionTest(String destinationFileName, int destinationFileNumber) {
        // given
        Position source = Position.of(File.E, Rank.FOUR);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnKnightRoute(destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"e,6", "g,4", "b,4", "e,2", "a,1"})
    @DisplayName("두 위치가 나이트가 이동 불가능한 경우를 올바르게 판단한다.")
    void invalidKnightPositionTest(String destinationFileName, int destinationFileNumber) {
        // given
        Position source = Position.of(File.E, Rank.FOUR);
        Position destination = createPositionByNameAndNumber(destinationFileName, destinationFileNumber);
        // when, then
        assertThat(source.isOnKnightRoute(destination)).isFalse();
    }

    private Position createPositionByNameAndNumber(String fileName, int rankNumber) {
        return Position.of(File.from(fileName), Rank.from(rankNumber));
    }
}
