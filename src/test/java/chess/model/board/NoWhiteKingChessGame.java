package chess.model.board;

import chess.model.ChessGame;

public class NoWhiteKingChessGame {

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
        chessGame.moveAndSaveRecord(PositionFixture.A2, PositionFixture.A3);
        chessGame.moveAndSaveRecord(PositionFixture.G8, PositionFixture.F6);
        chessGame.moveAndSaveRecord(PositionFixture.A3, PositionFixture.A4);
        chessGame.moveAndSaveRecord(PositionFixture.F6, PositionFixture.D5);
        chessGame.moveAndSaveRecord(PositionFixture.A4, PositionFixture.A5);
        chessGame.moveAndSaveRecord(PositionFixture.D5, PositionFixture.E3);
        chessGame.moveAndSaveRecord(PositionFixture.A5, PositionFixture.A6);
        chessGame.moveAndSaveRecord(PositionFixture.E3, PositionFixture.G2);
        chessGame.moveAndSaveRecord(PositionFixture.B2, PositionFixture.B3);
        chessGame.moveAndSaveRecord(PositionFixture.G2, PositionFixture.E1);
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
