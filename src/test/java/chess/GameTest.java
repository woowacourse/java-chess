package chess;

import chess.domain.game.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("게임")
class GameTest {

    @DisplayName("해당 턴이 아닌 경우 예외가 발생한다")
    @Test
    void invalidTurn() {
        //given
        String source = "a7";
        String destination = "a6";
        Game game = new Game();

        //when & then
        assertThatThrownBy(() -> game.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한번의 움직임 이후 턴이 바뀐다")
    @Test
    void exchangeTurn() {
        //given
        String source = "a2";
        String destination = "a3";
        Game game = new Game();

        //when
        game.movePiece(source, destination);

        //then
        assertThatThrownBy(() -> game.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class);
    }
}