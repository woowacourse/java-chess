package dao;

import chess.domain.Aliance;
import dto.GameDTO;

public interface GameDAO {
    GameDTO findById(int id);
    int addGame();
    int updateGame(GameDTO gameDTO);
    int deleteGame(int id);
}
