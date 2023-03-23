package repository;

public interface ChessDao {
    void addGame();

    void findAllGame();

    void findBoardByGameName();

    void deleteAllGame();

    void saveBoard();

    void saveMoveHistory();
}
