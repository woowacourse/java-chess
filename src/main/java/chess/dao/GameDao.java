package chess.dao;

import chess.domain.ChessGame;
import java.util.List;

public interface GameDao {
    List<Integer> findAllPossibleId();
    List<Integer> findAllImpossibleId();
    ChessGame findById(int gameId);
    void save(int gameId, ChessGame chessGame);
    void updateById(int gameId, ChessGame chessGame);
    void deleteById(int gameId);
}
