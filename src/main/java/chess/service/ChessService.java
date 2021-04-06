package chess.service;

import chess.domain.ChessGame;

public interface ChessService {

    ChessGame loadChess(Long gameId);

    void restart(Long gameId);

    void exitGame(Long gameId);

    void saveGame(Long gameId);
}
