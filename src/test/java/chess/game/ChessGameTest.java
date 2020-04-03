package chess.game;

import org.junit.jupiter.api.Test;

class ChessGameTest {
    @Test
    void calculateScore() {
        ChessGame chessGame = new ChessGame();
        System.out.println(chessGame.calculateScores().get(0).getValue());
        System.out.println(chessGame.calculateScores().get(1).getValue());
    }
}