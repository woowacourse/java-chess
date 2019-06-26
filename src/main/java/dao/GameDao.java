package dao;

import dto.GameDto;

public interface GameDao {
    GameDto findById(int id);
    int addGame();
    int updateGame(GameDto gameDTO);
    int deleteGameByid(int id);
}
