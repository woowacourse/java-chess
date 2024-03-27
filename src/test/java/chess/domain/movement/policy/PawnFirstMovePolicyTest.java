package chess.domain.movement.policy;

import static chess.domain.pixture.PositionFixture.BLACK_PAWN_FIRST_MOVE_POSITION;
import static chess.domain.pixture.PositionFixture.WHITE_PAWN_FIRST_MOVE_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnFirstMovePolicyTest {

    @Test
    @DisplayName("첫 이동 여부 정책과 일치 할 경우 해당 정책을 만족한다.")
    void isSatisfied() {
        PawnFirstMovePolicy policy = new PawnFirstMovePolicy();

        assertAll(
                () -> assertThat(
                        policy.isSatisfied(Color.BLACK, BLACK_PAWN_FIRST_MOVE_POSITION.getPosition(), false)).isTrue(),
                () -> assertThat(
                        policy.isSatisfied(Color.BLACK, BLACK_PAWN_FIRST_MOVE_POSITION.getPosition(), true)).isTrue(),
                () -> assertThat(
                        policy.isSatisfied(Color.WHITE, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), false)).isTrue(),
                () -> assertThat(
                        policy.isSatisfied(Color.WHITE, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), true)).isTrue()
        );
    }

    @Test
    @DisplayName("첫 이동 여부 정책과 일치 하지 않을 경우 해당 정책을 만족하지 않는다.")
    void isNotSatisfied() {
        PawnFirstMovePolicy policy = new PawnFirstMovePolicy();

        assertAll(
                () -> assertThat(
                        policy.isSatisfied(Color.BLACK, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), false)).isFalse(),
                () -> assertThat(
                        policy.isSatisfied(Color.BLACK, WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), true)).isFalse(),
                () -> assertThat(
                        policy.isSatisfied(Color.WHITE, BLACK_PAWN_FIRST_MOVE_POSITION.getPosition(), false)).isFalse(),
                () -> assertThat(
                        policy.isSatisfied(Color.WHITE, BLACK_PAWN_FIRST_MOVE_POSITION.getPosition(), true)).isFalse()
        );
    }
}
