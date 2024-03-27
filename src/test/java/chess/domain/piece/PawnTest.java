package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 대각선 전진 방향으로 공격할 수 있다.")
    void pawnAttackableTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        Position position = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isAttackable(position, Position.of(File.C, Rank.FIVE))).isTrue(),
                () -> assertThat(whitePawn.isAttackable(position, Position.of(File.E, Rank.FIVE))).isTrue(),
                () -> assertThat(blackPawn.isAttackable(position, Position.of(File.C, Rank.THREE))).isTrue(),
                () -> assertThat(blackPawn.isAttackable(position, Position.of(File.E, Rank.THREE))).isTrue()
        );
    }
}
