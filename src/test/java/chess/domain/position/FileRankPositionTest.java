package chess.domain.position;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.A8;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.B3;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileRankPositionTest {
    @DisplayName("x축 대칭인 포지션을 반환할 수 있다")
    @Test
    void should_ReturnVerticalReversePosition() {
        assertThat(A1.calculateVerticalReversedPosition()).isEqualTo(A8);
    }

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
}
