package chess.dao;

import chess.dto.game.ChessGameLoadDto;
import chess.dto.game.ChessGameSaveDto;

public final class TestDao implements ChessDao {
    @Override
    public void save(final ChessGameSaveDto dto) {

    }

    @Override
    public ChessGameLoadDto loadGame() {
        return null;
    }

    @Override
    public boolean hasHistory() {
        return false;
    }

    @Override
    public void delete() {

    }
}
