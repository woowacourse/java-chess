package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitPawnTest {

    @Test
    @DisplayName("최초 폰은 최대 2만큼 전진할 수 있다.")
    void initPawnMoveTest() {
        // given
        InitPawn whitePawn = new InitPawn(Color.WHITE);
        InitPawn blackPawn = new InitPawn(Color.BLACK);
        Position whitePosition = Position.of(File.B, Rank.TWO);
        Position blackPosition = Position.of(File.B, Rank.SEVEN);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isMovable(whitePosition, Position.of(File.B, Rank.THREE))).isTrue(),
                () -> assertThat(whitePawn.isMovable(whitePosition, Position.of(File.B, Rank.FOUR))).isTrue(),
                () -> assertThat(blackPawn.isMovable(blackPosition, Position.of(File.B, Rank.SIX))).isTrue(),
                () -> assertThat(blackPawn.isMovable(blackPosition, Position.of(File.B, Rank.FIVE))).isTrue()
        );
    }
}
