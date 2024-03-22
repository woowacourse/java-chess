package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovedPawnTest {

    @Test
    @DisplayName("폰은 최대 한 칸만 전진할 수 있다.")
    void pawnMoveTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        Position position = Position.of(File.B, Rank.THREE);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isMovable(position, Position.of(File.B, Rank.FOUR))).isTrue(),
                () -> assertThat(blackPawn.isMovable(position, Position.of(File.B, Rank.TWO))).isTrue()
        );
    }

    @Test
    @DisplayName("폰은 두 칸 이상 전진할 수 없다.")
    void pawnMaxUnitTest() {
        // given
        MovedPawn whitePawn = new MovedPawn(Color.WHITE);
        MovedPawn blackPawn = new MovedPawn(Color.BLACK);
        Position position = Position.of(File.B, Rank.THREE);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isMovable(position, Position.of(File.B, Rank.FIVE))).isFalse(),
                () -> assertThat(blackPawn.isMovable(position, Position.of(File.B, Rank.ONE))).isFalse()
        );
    }
}
