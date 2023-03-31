package chess.dao;

import chess.dao.entity.ChessGameEntity;
import chess.domain.ChessGame;

public interface ChessGameDao {

    long saveNewChessGame();
    long findRecentGameId();

    boolean isExistPreviousChessGame(long gameId);

    ChessGameEntity findChessGameByGameId(long gameId);

    void updateTurn(ChessGame chessGame);
}
