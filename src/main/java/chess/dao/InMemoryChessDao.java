package chess.dao;

import chess.domain.game.ChessGame;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryChessDao implements ChessDao {
    public final Map<Long, ChessGame> inMemoryDB = new HashMap<>();

    public InMemoryChessDao() {
    }

    @Override
    public long createChessGame(final ChessGame chessGame) {
        long id = inMemoryDB.size() + 1;
        inMemoryDB.put(id, chessGame);
        return id;
    }

    @Override
    public void addBoard(final long chessGameId, final ChessGame chessGame) throws SQLException {
        inMemoryDB.put(chessGameId, chessGame);
    }

    @Override
    public void deleteGame(final long chessGameId) throws SQLException {
        inMemoryDB.remove(chessGameId);
    }

    @Override
    public ChessGame findGameById(final long id) {
        return inMemoryDB.get(id);
    }

    @Override
    public List<Long> getRoomId() throws SQLException {
        return new ArrayList<>(inMemoryDB.keySet());
    }

    @Override
    public Map<Long, ChessGame> getDatabase() {
        return inMemoryDB;
    }
}
