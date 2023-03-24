package repository.game;

import java.util.List;

import dto.BoardDto;
import dto.MoveHistoryDto;

public interface GameDao {
    List<BoardDto> findBoardByRoomId(long gameId);

    void saveBoard(long game_id, List<BoardDto> boardDtos);

    void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto);

    void updateCurrentTurn(long gameId, String currentTurn);

    String findCurrentTurnByGameName(long roomId);

    void deleteBoardById(long gameId);

    List<MoveHistoryDto> findMoveHistoryByGameId(long game_id);

    List<MoveHistoryDto> findLastTwoMoveHistories(long gameId);
}
