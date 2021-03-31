package chess.repository;

import chess.domain.ChessGame;

public interface ChessRepository {

    ChessGame createGame(Long gameId);

    void endGame(Long gameId);

    void restart(Long gameId);

    void save(Long gameId);
}
