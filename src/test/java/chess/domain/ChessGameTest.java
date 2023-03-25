package chess.domain;

import chess.domain.chessgame.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.SquareCoordinates.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        assertThatThrownBy(() -> chessGame.move(A2, A4)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임이_정상적으로_종료되는지_확인() {
        assertThat(chessGame.isNotEnd()).isTrue();

        chessGame.end();
        assertThat(chessGame.isNotEnd()).isFalse();
    }

    @Test
    void 왕을_잡았을때_게임이_대기상태로_전환되는지_확인() {
        chessGame.start();

        //Shortest way for checkmate
        chessGame.move(F2, F3);
        chessGame.move(E7, E5);
        chessGame.move(G2, G4);
        chessGame.move(D8, H4);
        chessGame.move(H2, H3);
        chessGame.move(H4, E1);

        assertDoesNotThrow(() -> chessGame.start());
    }
}
