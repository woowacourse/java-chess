package chess.dao;

import chess.dao.entity.ChessGameEntity;

public interface ChessGameDao {

    Long saveNewChessGame();
    Long findRecentGameId();

    boolean isExistPreviousChessGame(Long gameId);

    ChessGameEntity findChessGameByGameId(Long gameId);

    void updateTurn(ChessGameEntity chessGameEntity);
}
