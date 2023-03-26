package chess.dao;

import chess.domain.ChessGame;
import java.util.List;

public class InMemoryChessGameDao implements ChessGameDao {

    @Override
    public long create() {
        return 1;
    }

    @Override
    public ChessGame findById(long id) {
        return ChessGame.createGame();
    }

    @Override
    public void updateTurn(ChessGame chessGame) {
        // nothing
    }

    @Override
    public List<Integer> findAll() {
        return null;
    }
}
