package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BoardFactory;
import chess.domain.board.ChessBoardFactory;
import chess.domain.board.GameOverBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임")
class GameTest {

    BoardFactory boardFactory;

    @BeforeEach
    void setUp() {
        boardFactory = new ChessBoardFactory();
    }

    @DisplayName("해당 턴이 아닌 경우 예외가 발생한다")
    @Test
    void invalidTurn() {
        //given
        String source = "a7";
        String target = "a6";
        Game game = new Game(1, boardFactory);

        //when & then
        assertThatThrownBy(() -> game.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한번의 움직임 이후 턴이 바뀐다")
    @Test
    void exchangeTurn() {
        //given
        String source = "a2";
        String target = "a3";
        Game game = new Game(1, boardFactory);

        //when
        game.movePiece(source, target);

        //then
        assertThatThrownBy(() -> game.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹이 잡힐 경우 게임이 종료한다")
    @Test
    void endGame() {
        //given
        BoardFactory gameOverBoardFactory = new GameOverBoardFactory();
        Game game = new Game(1, boardFactory);
        Game overGame = new Game(2, gameOverBoardFactory);

        //when
        GameResult result = game.getResult();
        GameResult overResult = overGame.getResult();

        //then
        assertThat(result.isGameOver()).isFalse();
        assertThat(overResult.isGameOver()).isTrue();
    }
}
