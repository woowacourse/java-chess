package repository;

import java.util.List;

import dto.BoardDto;
import dto.MoveHistoryDto;

public interface ChessDao {
    void addGame(String gameName);

    List<String> findAllGame();

    List<BoardDto> findBoardByGameName(String gameName);

    void deleteAllGame();

    void saveBoard(long game_id, List<BoardDto> boardDtos);

    void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto);
}
