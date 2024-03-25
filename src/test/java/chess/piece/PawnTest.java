package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 대각선 전진 방향으로 공격할 수 있다.")
    void pawnAttackTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        Position position = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.canAttack(position, Position.of(File.C, Rank.FIVE))).isTrue(),
                () -> assertThat(whitePawn.canAttack(position, Position.of(File.E, Rank.FIVE))).isTrue(),
                () -> assertThat(blackPawn.canAttack(position, Position.of(File.C, Rank.THREE))).isTrue(),
                () -> assertThat(blackPawn.canAttack(position, Position.of(File.E, Rank.THREE))).isTrue()
        );
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
