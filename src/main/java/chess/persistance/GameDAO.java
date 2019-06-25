package chess.persistance;

import chess.model.ChessPieceColor;

public interface GameDAO {
    static GameDAO getInstance() {
        return GameDAOImpl.getInstance();
    }

    void addGame(String gameName);

    int getGameId(String gameName);

    void removeGame(int gameId);

    ChessPieceColor getTurn(int gameId);

    int countGames();
}
