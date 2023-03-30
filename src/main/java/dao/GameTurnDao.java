package dao;

import dto.GameTurnDto;


public interface GameTurnDao {
    void save(GameTurnDto gameTurnDto);

    GameTurnDto find();

    void update(GameTurnDto gameTurnDto);

    void delete();
}
