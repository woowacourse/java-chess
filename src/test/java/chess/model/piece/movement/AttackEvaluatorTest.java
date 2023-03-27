package chess.model.piece.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.helper.CommonAttackEvaluator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AttackEvaluatorTest extends CommonAttackEvaluator {

    @Nested
    @DisplayName("source - black, target - white 테스트")
    class BlackEnemyEvaluatorTest {

        @Test
        @DisplayName("isEmpty()를 호출하면 false를 반환한다.")
        void isEmpty_whenCall_thenReturnFalse() {
            // when
            final boolean actual = blackEnemyEvaluator.isEmpty();

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("isEnemy()를 호출하면 true를 반환한다.")
        void isEnemy_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackEnemyEvaluator.isEnemy();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("isAttackAble()을 호출하면 true를 반환한다.")
        void isAttackAble_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackEnemyEvaluator.isAttackAble();

            // then
            assertThat(actual).isTrue();
        }
    }

    @Nested
    @DisplayName("source - black, target - empty 테스트")
    class BlackEmptyEvaluatorTest {

        @Test
        @DisplayName("isEmpty()를 호출하면 true를 반환한다.")
        void isEmpty_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackEmptyEvaluator.isEmpty();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("isEnemy()를 호출하면 true를 반환한다.")
        void isEnemy_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackEmptyEvaluator.isEnemy();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("isAttackAble()을 호출하면 true를 반환한다.")
        void isAttackAble_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackEmptyEvaluator.isAttackAble();

            // then
            assertThat(actual).isTrue();
        }
    }

    @Nested
    @DisplayName("source - black, target - black 테스트")
    class BlackAllyEvaluatorTest {

        @Test
        @DisplayName("isEmpty()를 호출하면 false를 반환한다.")
        void isEmpty_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackAllyEvaluator.isEmpty();

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("isEnemy()를 호출하면 false를 반환한다.")
        void isEnemy_whenCall_thenReturnTrue() {
            // when
            final boolean actual = blackAllyEvaluator.isEnemy();

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("isAttackAble()을 호출하면 false를 반환한다.")
        void isAttackAble_whenCall_thenReturnFalse() {
            // when
            final boolean actual = blackAllyEvaluator.isAttackAble();

            // then
            assertThat(actual).isFalse();
        }
    }
}
