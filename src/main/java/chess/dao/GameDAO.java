package chess.dao;

import chess.domain.ChessGame;

public interface GameDAO {
    void create(ChessGame chessGame);
    ChessGame readFromId(String id);
    void update(String id, ChessGame chessGame);
    int getRecentGameId();
}
