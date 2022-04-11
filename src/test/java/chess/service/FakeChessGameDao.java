package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.piece.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class FakeChessGameDao implements ChessGameDao {

    private final Map<Long, String> chessGame = new LinkedHashMap<>();

    @Override
    public Long save(Color turn) {
        chessGame.put((long) (chessGame.size() + 1), turn.getName());
        return (long) chessGame.size();
    }

    @Override
    public Color findTurnById(Long id) {
        return Color.from(chessGame.get(id));
    }

    @Override
    public void deleteById(Long id) {
        chessGame.remove(id);
    }

    @Override
    public void updateTurnById(Long id, String turn) {
        chessGame.put(id, turn);
    }

    public int getChessGameSize() {
        return chessGame.size();
    }
}
