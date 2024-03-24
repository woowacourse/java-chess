package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("file, rank를 입력받아 위치를 생성한다.")
    @Test
    void create() {
        // given
        final Position positionA_ONE = new Position(File.A, Rank.ONE);
        final Position positionB_TWO = new Position(File.B, Rank.TWO);

        // when && then
        assertThat(positionA_ONE).isEqualTo(new Position(File.A, Rank.ONE));
        assertThat(positionB_TWO).isEqualTo(new Position(File.B, Rank.TWO));
    }

    @DisplayName("한칸 위의 위치를 반환한다.")
    @Test
    void up() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);

        // when
        Position expectedPosition = sourcePosition.up();

        // then
        assertThat(expectedPosition).isEqualTo(new Position(File.C, Rank.FOUR));
    }

    @DisplayName("한칸 오른쪽의 위치를 반환한다.")
    @Test
    void right() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);

        // when
        Position expectedPosition = sourcePosition.right();

        // then
        assertThat(expectedPosition).isEqualTo(new Position(File.D, Rank.THREE));
    }

    @DisplayName("한칸 오른쪽위의 위치를 반환한다.")
    @Test
    void rightUp() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);

        // when
        Position expectedPosition = sourcePosition.rightUp();

        // then
        assertThat(expectedPosition).isEqualTo(new Position(File.D, Rank.FOUR));
    }

    @DisplayName("한칸 왼쪽위의 위치를 반환한다.")
    @Test
    void leftUp() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);

        // when
        Position expectedPosition = sourcePosition.leftUp();

        // then
        assertThat(expectedPosition).isEqualTo(new Position(File.B, Rank.FOUR));
    }

    @DisplayName("대각선 관계임을 확인한다..")
    @Test
    void canMoveDiagonal() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position targetPositionRightUp = new Position(File.E, Rank.SIX);
        final Position targetPositionRightDown = new Position(File.E, Rank.FOUR);
        final Position targetPositionLeftUP = new Position(File.C, Rank.SIX);
        final Position targetPositionLeftDown = new Position(File.C, Rank.FOUR);

        // when
        final boolean resultRightUp = sourcePosition.isDiagonalWith(targetPositionRightUp);
        final boolean resultRightDown = sourcePosition.isDiagonalWith(targetPositionRightDown);
        final boolean resultLeftUP = sourcePosition.isDiagonalWith(targetPositionLeftUP);
        final boolean resultLeftDown = sourcePosition.isDiagonalWith(targetPositionLeftDown);

        // then
        assertThat(resultRightUp).isTrue();
        assertThat(resultRightDown).isTrue();
        assertThat(resultLeftUP).isTrue();
        assertThat(resultLeftDown).isTrue();
    }

    @DisplayName("대각선 관계가 아님을 확인한다.")
    @Test
    void canNotMoveDiagonal() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position notMovablePosition = new Position(File.D, Rank.SIX);

        // when
        final boolean result = sourcePosition.isDiagonalWith(notMovablePosition);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("다른 위치와의 랭크상 거리를 구한다.")
    @Test
    void getRankDistance() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position targetPosition = new Position(File.D, Rank.SEVEN);

        // when
        int rankDistance = sourcePosition.getRankDistance(targetPosition);

        // then
        assertThat(rankDistance).isEqualTo(2);
    }

    @DisplayName("다른 위치와의 파일상 거리를 구한다.")
    @Test
    void getFileDistance() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position targetPosition = new Position(File.A, Rank.SEVEN);

        // when
        int rankDistance = sourcePosition.getFileDistance(targetPosition);

        // then
        assertThat(rankDistance).isEqualTo(3);
    }
}
