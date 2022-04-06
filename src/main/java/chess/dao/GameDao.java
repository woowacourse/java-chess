package chess.dao;

import chess.dto.GameDto;

public interface GameDao {


    void removeAll();

    void save(GameDto gameDto);

    void update(GameDto gameDto);

    void updateTurn(String turn);

    void updateStatus(String status);

    GameDto find();
}
