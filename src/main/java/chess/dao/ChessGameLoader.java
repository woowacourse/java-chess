package chess.dao;

import chess.domain.game.ChessGame;

public class ChessGameLoader {
    public static ChessGame load(ChessGameDao chessGameDao) {
        // 조회
        ChessGame chessGame = chessGameDao.select();
        if (chessGame == null) {
            chessGame = new ChessGame();
            // 생성 후 저장
            chessGameDao.save(chessGame);
        }
        return chessGame;
    }
}
