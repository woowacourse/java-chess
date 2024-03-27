package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.UnitDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"1,1", "-1,1"})
    @DisplayName("백색 폰은 대각선 전진 방향으로 공격할 수 있다.")
    void whitePawnAttackTest(int fileDifference, int rankDifference) {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        UnitDirection direction = UnitDirection.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = whitePawn.canAttack(direction, 1);
        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "-1,-1"})
    @DisplayName("흑색 폰은 대각선 전진 방향으로 공격할 수 있다.")
    void blackPawnAttackTest(int fileDifference, int rankDifference) {
        // given
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        UnitDirection direction = UnitDirection.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = blackPawn.canAttack(direction, 1);
        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("폰 여부를 올바르게 판단한다.")
    void isPawnTest() {
        // given
        MovedPawn movedPawn = new MovedPawn(Color.WHITE);
        // when, then
        assertAll(
                () -> assertThat(movedPawn.isPawn()).isTrue(),
                () -> assertThat(movedPawn.isNotPawn()).isFalse()
        );
    }

    @Test
    @DisplayName("폰이 아닌 경우의 폰 여부를 올바르게 판단한다.")
    void isPawnOnOtherPieceTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        // when, then
        assertAll(
                () -> assertThat(bishop.isPawn()).isFalse(),
                () -> assertThat(bishop.isNotPawn()).isTrue()
        );
    }
}
