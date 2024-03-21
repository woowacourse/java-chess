package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitPawnTest {

    @Test
    @DisplayName("최초 폰은 최대 2만큼 전진할 수 있다.")
    void initPawnMoveTest() {
        // given
        InitPawn whitePawn = new InitPawn(Color.WHITE);
        InitPawn blackPawn = new InitPawn(Color.BLACK);
        Position whitePosition = Position.of("b", 2);
        Position blackPosition = Position.of("b", 7);
        // when, then
        assertAll(
                () -> assertThat(whitePawn.isMovable(whitePosition, Position.of("b", 3))).isTrue(),
                () -> assertThat(whitePawn.isMovable(whitePosition, Position.of("b", 4))).isTrue(),
                () -> assertThat(blackPawn.isMovable(blackPosition, Position.of("b", 6))).isTrue(),
                () -> assertThat(blackPawn.isMovable(blackPosition, Position.of("b", 5))).isTrue()
        );
    }
}
