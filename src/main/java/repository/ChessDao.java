package repository;

import java.util.List;

public interface ChessDao {
    void addGame(String gameName);

    List<String> findAllGame();

    void findBoardByGameName();

    void deleteAllGame();

    void saveBoard();

    void saveMoveHistory();
}
