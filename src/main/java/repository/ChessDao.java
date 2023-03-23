package repository;

import java.util.List;

import dto.MoveHistoryDto;

public interface ChessDao {
    void addGame(String gameName);

    List<String> findAllGame();

    void findBoardByGameName();

    void deleteAllGame();

    void saveBoard();

    void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto);
}
