package chessgame.db;

import java.util.List;

import chessgame.domain.Board;
import chessgame.domain.Game;

public interface BoardDao {
    List<Integer> findRunningBoards();

    void addBoard();

    int findLastBoardNo();

    Board findBoardByNo(int boardNo);

    void addPoints(int boardNo);

    void updatePoints(Board board);

    void updateBoardState(Game game);

    String getState(int boardNo);

    boolean isNotRunning(int boardNo);
}
