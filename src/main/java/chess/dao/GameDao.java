package chess.dao;

import chess.dto.GameInfoDto;
import java.util.List;

public interface GameDao {
    List<Integer> findAllPossibleId();
    List<Integer> findAllImpossibleId();
    GameInfoDto findById(int gameId);
    void create(int gameId, GameInfoDto gameInfoDto);
    void updateById(int gameId, GameInfoDto gameInfoDto);
    void deleteById(int gameId);
    void deleteAll();
}
