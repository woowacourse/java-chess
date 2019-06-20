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

    @Test
    void 게임_결과() {
        ChessGame game = new ChessGame();
        ChessResult whiteResult = game.result(ChessTeam.WHITE);
        assertEquals(38.0, whiteResult.score(), 0.1);
    }

    @Test
    void name() {
        ChessGame game = new ChessGame();
        game.play("move b2 b4");
        game.play("move b8 c6");
        game.play("move c1 a3");
        game.play("move c6 b4");
        game.play("move a3 b4");
        ChessResult whiteResult = game.result(ChessTeam.WHITE);
        assertEquals(37.0, whiteResult.score(), 0.1);
        ChessResult blakcResult = game.result(ChessTeam.BLACK);
        assertEquals(35.5, blakcResult.score(), 0.1);
    }

    @Test
    void 폰이_같은선상에_존재할_때_테스트() {
        ChessGame game = new ChessGame();
        game.play("move c2 c4");
        game.play("move d7 d5");
        game.play("move c4 d5");
        ChessResult whiteResult = game.result(ChessTeam.WHITE);
        assertEquals(37.0, whiteResult.score(), 0.1);
        ChessResult blakcResult = game.result(ChessTeam.BLACK);
        assertEquals(37.0, blakcResult.score(), 0.1);
    }
}
