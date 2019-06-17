package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessGameTest {
    @Test
    void play_테스트_move가_아닐_때() {
        ChessGame chessGame = new ChessGame();
        assertThrows(IllegalArgumentException.class, () -> chessGame.play("to b1 b2"));
    }

    @Test
    void play_테스트_입력이_잘못되었을_때() {
        ChessGame chessGame = new ChessGame();
        assertThrows(IllegalArgumentException.class, () -> chessGame.play("move b1"));
    }
}
