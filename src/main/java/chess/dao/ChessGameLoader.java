package chess.dao;

import chess.domain.game.ChessGame;

public class ChessGameLoader {
    public static ChessGame load(ChessGameDao chessGameDao) {
        ChessGame chessGame = chessGameDao.findChessGame();
        if (chessGame == null) {
            chessGame = new ChessGame();
            chessGameDao.save(chessGame);
        }
        return chessGame;
    }
}
