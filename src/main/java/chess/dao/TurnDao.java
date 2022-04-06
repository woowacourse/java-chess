package chess.dao;

import chess.web.dto.TurnDto;
import java.util.Optional;

public interface TurnDao {

    void save(TurnDto turnDto);

    Optional<TurnDto> findLastTurn();
}
