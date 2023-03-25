package chess.dao;

import chess.dto.game.ChessGameLoadDto;
import chess.dto.game.ChessGameSaveDto;

public interface ChessDao {

    void save(final ChessGameSaveDto dto);

    ChessGameLoadDto loadGame();

    boolean hasHistory();

    void delete();
}
