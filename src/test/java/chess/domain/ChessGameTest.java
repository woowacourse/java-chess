package chess.domain;

import chess.domain.chessboard.SquareCoordinate;
import chess.domain.chessgame.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    private static ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    void 게임을_진행_중에_새로운_게임을_시작하는_예외_테스트() {
        chessGame.start();
        assertThatThrownBy(() -> chessGame.start()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임을_시작하기_전에_말을_움직이는_예외_테스트() {
        assertThatThrownBy(() -> chessGame.move(SquareCoordinate.of("a2"), SquareCoordinate.of("a3"))).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임이_정상적으로_종료되는지_확인() {
        assertThat(chessGame.isNotEnd()).isTrue();

        chessGame.end();
        assertThat(chessGame.isNotEnd()).isFalse();
    }
}
