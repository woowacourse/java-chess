package chess.model.dao;

import chess.model.dao.PieceDao;
import chess.model.domain.board.Board;
import chess.model.domain.board.Turn;
import chess.model.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class MemoryPieceDao implements PieceDao {

    private final Map<Long, Board> boards = new HashMap<>();

    @Override
    public void saveBoard(final Board board, final long gameId) {
        boards.put(gameId, board);
    }

    @Override
    public void updatePiecePosition(final Position from, final Position to, final long gameId) {
        final Board board = boards.get(gameId);
        board.move(from, to);
    }

    @Override
    public Board loadBoard(final Long gameId, final Turn turn) {
        return null;
    }

    @Override
    public void deleteBoard(final long gameId) {

    }
}
