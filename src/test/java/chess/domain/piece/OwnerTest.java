package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "인자로 받는 Owner가 상대편의 색깔인지 판단한다.")
    @CsvSource({"BLACK, WHITE, true", "BLACK, BLACK, false"})
    void isEnemyTest(String ownerName, String ownerToCompareName, boolean isDifferent) {
        //given
        Owner owner = Owner.valueOf(ownerName);
        Owner ownerToCompare = Owner.valueOf(ownerToCompareName);

        //then
        assertThat(owner.isEnemy(ownerToCompare)).isEqualTo(isDifferent);
    }

    @Test
    @DisplayName("NONE 에 isEnemy를 사용하면 예외가 발생한다.")
    void noneIsEnemyThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Owner.NONE.isEnemy(Owner.BLACK);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("적이 존재하지 않는 색입니다.");
    }

    @ParameterizedTest(name = "인자로 받는 Owner가 같은 색깔인지 판단한다.")
    @CsvSource({"BLACK, BLACK, true", "BLACK, WHITE, false"})
    void isSameTest(String ownerName, String ownerToCompareName, boolean isDifferent) {
        //given
        Owner owner = Owner.valueOf(ownerName);
        Owner ownerToCompare = Owner.valueOf(ownerToCompareName);

        //then
        assertThat(owner.isSame(ownerToCompare)).isEqualTo(isDifferent);
    }

    @ParameterizedTest(name = "인자로 받는 Owner가 다른 Owner 인지 확인한다.")
    @CsvSource({"BLACK, WHITE, true", "WHITE, NONE, true", "BLACK, BLACK, false", "NONE, NONE, false"})
    void isDifferent(String ownerName, String ownerToCompareName, boolean isDifferent) {
        //given
        Owner owner = Owner.valueOf(ownerName);
        Owner ownerToCompare = Owner.valueOf(ownerToCompareName);

        //then
        assertThat(owner.isDifferent(ownerToCompare)).isEqualTo(isDifferent);
    }


}