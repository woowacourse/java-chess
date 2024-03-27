package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PathFinderTest {

    @DisplayName("랭크 방향으로 직선 경로를 찾는다.")
    @Test
    void findRankStraightPath() {
        final Position start = new Position(Rank.FIRST, File.A);
        final Position target = new Position(Rank.FOURTH, File.A);
        final Set<Position> expected = Set.of(new Position(Rank.SECOND, File.A), new Position(Rank.THIRD, File.A));

        final PathFinder pathFinder = new PathFinder(start, target);
        final Set<Position> foundPath = pathFinder.find();

        assertThat(foundPath).isEqualTo(expected);
    }

    @DisplayName("파일 방향으로 직선 경로를 찾는다.")
    @Test
    void findFileStraightPath() {
        final Position start = new Position(Rank.FIRST, File.A);
        final Position target = new Position(Rank.FIRST, File.D);
        final Set<Position> expected = Set.of(new Position(Rank.FIRST, File.B), new Position(Rank.FIRST, File.C));

        final PathFinder pathFinder = new PathFinder(start, target);
        final Set<Position> foundPath = pathFinder.find();

        assertThat(foundPath).isEqualTo(expected);
    }

    @DisplayName("Uphill 방향의 대각선 경로를 찾는다.")
    @Test
    void findUphillDiagonalPath() {
        final Position start = new Position(Rank.FIRST, File.A);
        final Position target = new Position(Rank.FOURTH, File.D);
        final Set<Position> expected = Set.of(new Position(Rank.SECOND, File.B), new Position(Rank.THIRD, File.C));

        final PathFinder pathFinder = new PathFinder(start, target);
        final Set<Position> foundPath = pathFinder.find();

        assertThat(foundPath).isEqualTo(expected);
    }

    @DisplayName("Downhill 방향의 대각선 경로를 찾는다.")
    @Test
    void findDownhillDiagonalPath() {
        final Position start = new Position(Rank.FOURTH, File.A);
        final Position target = new Position(Rank.FIRST, File.D);
        final Set<Position> expected = Set.of(new Position(Rank.THIRD, File.B), new Position(Rank.SECOND, File.C));

        final PathFinder pathFinder = new PathFinder(start, target);
        final Set<Position> foundPath = pathFinder.find();

        assertThat(foundPath).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 직선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "FIRST, A, FOURTH, A, true",
            "FIRST, A, FIRST, D, true",
            "FIRST, A, FOURTH, B, false"
    })
    void checkWhetherStraightPath(Rank startRank, File startFile, Rank targetRank, File targetFile, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isStraight = pathFinder.isStraight();

        assertThat(isStraight).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 최대 거리보다 짧거나 같은 직선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "FIRST, A, FOURTH, A, 3, true",
            "FIRST, A, FOURTH, A, 2, false",
            "FIRST, A, FIRST, D, 3, true",
            "FIRST, A, FIRST, D, 2, false",
            "FIRST, A, FOURTH, B, 1, false"
    })
    void checkWhetherLessThanMaxDistanceAndStraight(Rank startRank, File startFile, Rank targetRank, File targetFile,
                                             int maxDistance, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isStraight = pathFinder.isStraight(maxDistance);

        assertThat(isStraight).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 대각선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "FIRST, A, FOURTH, D, true",
            "FOURTH, A, FIRST, D, true",
            "FIRST, A, FOURTH, A, false"
    })
    void checkWhetherDiagonal(Rank startRank, File startFile, Rank targetRank, File targetFile, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isDiagonal = pathFinder.isDiagonal();

        assertThat(isDiagonal).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 최대 거리보다 짧거나 같은 대각선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "FIRST, A, FOURTH, D, 3, true",
            "FIRST, A, FOURTH, D, 2, false",
            "FOURTH, A, FIRST, D, 3, true",
            "FOURTH, A, FIRST, D, 2, false",
            "FIRST, A, FOURTH, A, 2, false"
    })
    void checkWhetherLessThanMaxDistanceAndDiagonal(Rank startRank, File startFile, Rank targetRank, File targetFile,
                                                    int maxDistance, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isDiagonal = pathFinder.isDiagonal(maxDistance);

        assertThat(isDiagonal).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 최대 거리보다 짧거나 같은 아래 방향 직선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "THIRD, A, FIRST, A, 2, true",
            "FIRST, A, THIRD, A, 2, false",
            "THIRD, A, FIRST, A, 1, false",
    })
    void checkWhetherLessThanMaxDistanceAndDown(Rank startRank, File startFile, Rank targetRank, File targetFile,
                                                    int maxDistance, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isDiagonal = pathFinder.isDown(maxDistance);

        assertThat(isDiagonal).isEqualTo(expected);
    }

    @DisplayName("주어진 경로가 최대 거리보다 짧거나 같은 위 방향 직선 경로인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "FIRST, A, THIRD, A, 2, true",
            "THIRD, A, FIRST, A, 2, false",
            "FIRST, A, THIRD, A, 1, false"
    })
    void checkWhetherLessThanMaxDistanceAndUp(Rank startRank, File startFile, Rank targetRank, File targetFile,
                                                int maxDistance, boolean expected) {
        final Position start = new Position(startRank, startFile);
        final Position target = new Position(targetRank, targetFile);

        final PathFinder pathFinder = new PathFinder(start, target);
        final boolean isDiagonal = pathFinder.isUp(maxDistance);

        assertThat(isDiagonal).isEqualTo(expected);
    }
}
