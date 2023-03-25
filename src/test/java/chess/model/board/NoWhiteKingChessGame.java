package chess.model.board;

import chess.dao.MoveTruncator;
import chess.model.ChessGame;

public class NoWhiteKingChessGame extends MoveTruncator {

    private ChessGame chessGame;

    /**
     * RNBQKB.R
     * PPPPPPPP
     * p.......
     * ........
     * ........
     * .p......
     * ..pppp.p
     * rnbqNbnr
     */
    public NoWhiteKingChessGame(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public static NoWhiteKingChessGame create() {
        final ChessGame chessGame = new ChessGame();
        killWhiteKing(chessGame);
        return new NoWhiteKingChessGame(chessGame);
    }

    private static void killWhiteKing(final ChessGame chessGame) {
        chessGame.move(PositionFixture.A2, PositionFixture.A3);
        chessGame.move(PositionFixture.G8, PositionFixture.F6);
        chessGame.move(PositionFixture.A3, PositionFixture.A4);
        chessGame.move(PositionFixture.F6, PositionFixture.D5);
        chessGame.move(PositionFixture.A4, PositionFixture.A5);
        chessGame.move(PositionFixture.D5, PositionFixture.E3);
        chessGame.move(PositionFixture.A5, PositionFixture.A6);
        chessGame.move(PositionFixture.E3, PositionFixture.G2);
        chessGame.move(PositionFixture.B2, PositionFixture.B3);
        chessGame.move(PositionFixture.G2, PositionFixture.E1);
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
