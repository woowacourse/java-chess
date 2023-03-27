package chess.dao;

import chess.dto.MoveDto;
import java.util.List;

public interface ChessGameDao {

    void saveMove(MoveDto moveDto, int gameId);

    List<MoveDto> findByGameId(int gameId);

    int findGameIdByNotFinished();

    void saveGame();

    void finishedGame();
}
