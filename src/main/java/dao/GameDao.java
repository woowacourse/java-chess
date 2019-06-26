package dao;

import dto.GameDto;

import java.util.List;

public interface GameDao {
    GameDto findById(int id);
    List<GameDto> findNotEndGames();
    int addGame();
    int updateGame(GameDto gameDTO);
    int deleteGameByid(int id);
}
