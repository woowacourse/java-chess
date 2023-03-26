package chess.dao;

import chess.domain.ChessGame;
import java.util.Collections;
import java.util.List;

public class InMemoryChessGameDao implements ChessGameDao {

    private ChessGame chessGame;

    @Override
    public long create() {
        return 1;
    }

    @Override
    public ChessGame findById(long id) {
        return chessGame;
    }

    @Override
    public void updateTurn(ChessGame chessGame) {
        // nothing
    }

    @Override
    public List<Integer> findAll() {
        return Collections.emptyList();
    }
}
