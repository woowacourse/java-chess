package chess.domain.position;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.B3;
import static chess.fixture.PositionFixtures.B4;
import static chess.fixture.PositionFixtures.C1;
import static chess.fixture.PositionFixtures.C3;
import static chess.fixture.PositionFixtures.D1;
import static chess.fixture.PositionFixtures.D4;
import static chess.fixture.PositionFixtures.E1;
import static chess.fixture.PositionFixtures.E5;
import static chess.fixture.PositionFixtures.F1;
import static chess.fixture.PositionFixtures.F6;
import static chess.fixture.PositionFixtures.G1;
import static chess.fixture.PositionFixtures.G7;
import static chess.fixture.PositionFixtures.H1;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("다른 포지션과 수평 관계에 있으면 Orthogonal하다")
    @Test
    void should_HaveOrthogonalRelationship_When_OtherIsHorizontalWithGivenPosition() {
        assertThat(A1.isOrthogonalWith(B1)).isTrue();
    }

    @DisplayName("다른 포지션과 수직 관계에 있으면 Orthogonal하다")
    @Test
    void should_HaveOrthogonalRelationship_When_OtherIsVerticalWithGivenPosition() {
        assertThat(A1.isOrthogonalWith(A3)).isTrue();
    }

    @DisplayName("대상 위치가 같은 위치라면 Orthogoanl하지 않다")
    @Test
    void should_IsNoOrthogonalRelationship_When_OtherIsSamePosition() {
        assertThat(A1.isOrthogonalWith(A1)).isFalse();
    }

    @DisplayName("다른 포지션과 수직 혹은 수평 관계에 있지 않으면 Orthogonal하지 않다")
    @Test
    void should_CheckPositionIsOrthogonalWithOther() {
        assertThat(A1.isOrthogonalWith(B3)).isFalse();
    }

    @DisplayName("파일 거리와 랭크 거리가 같으면 Diagonal 관계이다")
    @Test
    void should_CheckPositionIsDiagonalWithOher() {
        assertThat(A1.isDiagonalWith(B2)).isTrue();
    }

    @DisplayName("대상 위치가 같은 위치라면 Diagonal관계가 아니다")
    @Test
    void should_IsNoDiagonalRelationship_When_OtherIsSamePosition() {
        assertThat(A1.isDiagonalWith(A1)).isFalse();
    }

    @DisplayName("다른 포지션과 비교했을 때 더 왼쪽에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsFurtherLeftThanOther() {
        assertThat(A1.isFurtherLeftThan(B1)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 더 오른쪽에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsFurtherRightThanOther() {
        assertThat(B1.isFurtherRightThan(A1)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 더 아래에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsBelowThanOther() {
        assertThat(A1.isBelow(A2)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 더 왼쪽에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsAboveThanOther() {
        assertThat(A2.isAbove(A1)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 왼쪽 아래에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsLeftLowerThanOther() {
        assertThat(A1.isLeftLowerThan(B2)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 왼쪽 위에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsLeftUpperThanOther() {
        assertThat(A2.isLeftUpperThan(B1)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 오른쪽 아래에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsRightLowerThanOther() {
        assertThat(B1.isRightLowerThan(A2)).isTrue();
    }

    @DisplayName("다른 포지션과 비교했을 때 오른쪽 위에 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionIsRightUpperThanOther() {
        assertThat(B2.isRightUpperThan(A1)).isTrue();
    }

    @DisplayName("수직 이동 경로를 계산할 수 있다")
    @Test
    void should_CalculateOrthogonalPath_When_DestinationIsGiven() {
        assertThat(A1.calculateSlidingPath(H1)).containsExactly(B1, C1, D1, E1, F1, G1);
    }

    @DisplayName("대각 이동 경로를 계산할 수 있다")
    @Test
    void should_CalculateDiagonalPath_When_DestinationIsGiven() {
        assertThat(A1.calculateSlidingPath(H8)).containsExactly(B2, C3, D4, E5, F6, G7);
    }

    @DisplayName("이동하기 위해 몇 칸을 지나야하는지 계산할 수 있다")
    @Test
    void should_CalculateCellDistance_When_DestinationIsGiven() {
        assertThat(A1.calculateDistance(B4)).isEqualTo(3);
    }

    @DisplayName("포지션 간 제곱 거리를 계산할 수 있다")
    @Test
    void should_CalculateSquaredDistance_When_DestinationIsGiven() {
        assertThat(A1.squaredDistanceWith(B3)).isEqualTo(5);
    }

    @DisplayName("포지션이 특정 랭크를 가지는지 확인할 수 있다")
    @Test
    void should_CompareRank_When_RankIsGiven() {
        assertThat(A1.isRankSameWith(Rank.ONE)).isTrue();
    }
}
