package repository.game;

import java.util.List;

import dto.BoardDto;
import dto.MoveHistoryDto;

public interface GameDao {
    List<BoardDto> findBoardByGameName(String gameName);

    void saveBoard(long game_id, List<BoardDto> boardDtos);

    void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto);

    void updateCurrentTurn(long gameId, String currentTurn);

    String findCurrentTurnByGameName(String gameName);

    void deleteBoardById(long gameId);

    List<MoveHistoryDto> findMoveHistoryByGameId(long game_id);
}
