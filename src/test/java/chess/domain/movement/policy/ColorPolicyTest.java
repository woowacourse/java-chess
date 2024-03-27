package chess.domain.movement.policy;

import static chess.domain.pixture.PositionFixture.PAWN_NOT_FIRST_MOVE_POSITION;
import static chess.domain.pixture.PositionFixture.WHITE_PAWN_FIRST_MOVE_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorPolicyTest {

    @ParameterizedTest
    @CsvSource({"false", "true"})
    @DisplayName("색깔 정책과 일치하는 색일 경우 해당 정책을 만족한다.")
    void isSatisfied(boolean existEnemy) {
        ColorPolicy policy = new ColorPolicy(Color.WHITE);

        assertAll(
                () -> assertThat(policy.isSatisfied(Color.WHITE, PAWN_NOT_FIRST_MOVE_POSITION.getPosition(),
                        existEnemy)).isTrue(),
                () -> assertThat(policy.isSatisfied(Color.WHITE, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(),
                        existEnemy)).isTrue());
    }

    @ParameterizedTest
    @CsvSource({"false", "true"})
    @DisplayName("색깔 정책과 일치하는 색일 경우 해당 정책을 만족하지 않는다.")
    void isNotSatisfied(boolean existEnemy) {
        ColorPolicy policy = new ColorPolicy(Color.WHITE);

        assertThat(policy.isSatisfied(Color.BLACK, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), existEnemy)).isFalse();
    }
}
