package chess.dao;

import chess.service.Move;
import java.util.List;

public interface ChessGameDao {

    void saveMove(Move move, int gameId);

    List<Move> findByGameId(int gameId);

    int findGameIdByNotFinished();

    void saveGame();

    void finishedGame();

    boolean isExistNotFinishedGame();
}
