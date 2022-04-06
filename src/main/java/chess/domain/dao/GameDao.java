package chess.domain.dao;

import chess.domain.dto.GameDto;

public interface GameDao {

    Long save(GameDto gameDto);
}
