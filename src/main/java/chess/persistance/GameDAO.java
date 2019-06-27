package chess.persistance;

import chess.model.piece.ChessPieceColor;

import java.util.List;

public interface GameDAO {
    static GameDAO getInstance() {
        return GameDAOImpl.getInstance();
    }

    void addGame(String gameName);

    int getGameId(String gameName);

    void removeGame(int gameId);

    ChessPieceColor getTurn(int gameId);

    int countGames();

    List<Integer> getAllIds();

    String getName(Integer id);

    void setTurn(ChessPieceColor turn, String gameId);
}
