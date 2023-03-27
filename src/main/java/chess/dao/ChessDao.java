package chess.dao;

import chess.dto.MoveDto;
import java.util.List;

public interface ChessDao {
    void saveHistory(MoveDto moveDto, int notFinishedGameId);

    List<MoveDto> selectAllHistory(int notFinishedGameId);

    void saveInitialGame();

    int findNotFinishedGameId();

    boolean existCurrentGame();

    void setGameFinished(final int gameId);
}
