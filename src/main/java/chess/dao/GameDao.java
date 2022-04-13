package chess.dao;

import chess.dto.response.ChessGameDto;

public interface GameDao {
    ChessGameDto getGame(String gameId);

    void createGame(String gameId);

    void deleteGame(String gameId);

    void updateTurnToWhite(String gameId);

    void updateTurnToBlack(String gameId);
}
