package chess.dao;

import chess.dto.GameDto;
import chess.dto.GameStatusDto;

public interface GameDao {


    void removeAll();

    void save(GameDto gameDto);

    void update(GameDto gameDto);

    void updateStatus(GameStatusDto statusDto);

    GameDto find();
}
