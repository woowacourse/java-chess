package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OwnerTest {

    @Test
    @DisplayName("현재 색깔을 반전 시켜준다.")
    void reverseTest() {
        //given
        Owner blackReverseOwner = Owner.BLACK.reverse();
        Owner whiteReverseOwner = Owner.WHITE.reverse();

        //then
        assertThat(blackReverseOwner).isEqualTo(Owner.WHITE);
        assertThat(whiteReverseOwner).isEqualTo(Owner.BLACK);
    }

    @Test
    @DisplayName("NONE을 반전시키려면 예외가 발생한다.")
    void noneReverseThrowExceptionTest() {
        assertThatThrownBy(Owner.NONE::reverse)
                .isInstanceOf(IllegalStateException.class).hasMessage("유효하지 않은 색깔입니다.");
    }

    @Test
    @DisplayName("상대편의 색깔인지 판단한다.")
    void isEnemyTest() {
        //given
        boolean isTrue = Owner.BLACK.isEnemy(Owner.WHITE);
        boolean isFalse = Owner.BLACK.isEnemy(Owner.BLACK);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("NONE 에 isEnemy를 사용하면 예외가 발생한다.")
    void noneIsEnemyThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Owner.NONE.isEnemy(Owner.BLACK);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("적이 존재하지 않는 색입니다.");
    }

    @Test
    @DisplayName("같은 색깔인지 판단한다.")
    void isSameTest() {
        //given
        boolean isTrue = Owner.BLACK.isSame(Owner.BLACK);
        boolean isFalse = Owner.BLACK.isSame(Owner.WHITE);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("다른 요소인지 확인한다.")
    void isDifferent() {
        //given
        boolean isTrue = Owner.BLACK.isDifferent(Owner.WHITE);
        boolean isTrue2 = Owner.WHITE.isDifferent(Owner.NONE);
        boolean isFalse = Owner.BLACK.isDifferent(Owner.BLACK);
        boolean isFalse2 = Owner.NONE.isDifferent(Owner.NONE);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isTrue2).isTrue();
        assertThat(isFalse).isFalse();
        assertThat(isFalse2).isFalse();
    }


}