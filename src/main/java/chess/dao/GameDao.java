package chess.dao;

import chess.dto.TurnDto;
import java.sql.Connection;

public interface GameDao {

    TurnDto getTurn();

    void changeTurn();
}
