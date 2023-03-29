package chess.dao;

import chess.dao.entity.ChessGameEntity;

public interface ChessGameDao {

    long saveNewChessGame();
    long findRecentGameId();

    boolean isExistPreviousChessGame(long gameId);

    ChessGameEntity findChessGameByGameId(long gameId);

    void updateTurn(ChessGameEntity chessGameEntity);
}
