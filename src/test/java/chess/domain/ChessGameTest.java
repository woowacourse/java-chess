package chess.domain;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.Empty;
import chess.domain.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void 게임_턴_위반() {
        ChessGame game = new ChessGame();
        game.play("move b2 b3");
        assertEquals(new Pawn(ChessTeam.WHITE), game.get(Point.get(2, 3)));
        assertThrows(IllegalArgumentException.class, () -> game.play("move b1 b3"));
    }

    @Test
    void 긴_게임_플레이() {
        ChessGame game = new ChessGame();
        game.play("move b2 b4");
        game.play("move b8 c6");
        game.play("move c1 a3");
        game.play("move c6 b4");
        game.play("move a3 b4");
        assertEquals(new Bishop(ChessTeam.WHITE), game.get(Point.get(2, 4)));
        assertEquals(new Empty(), game.get(Point.get(1, 3)));
        assertEquals(new Empty(), game.get(Point.get(3, 6)));
        assertEquals(new Empty(), game.get(Point.get(3, 1)));
    }
}
