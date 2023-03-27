package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.position.Position;

public class MemoryBoardDao implements BoardDao {

    @Override
    public void saveBoard(final Board board, final long gameId) {

    }

    @Override
    public void updatePiecePosition(final Position from, final Position to, final long gameId) {

    }

    @Override
    public Board loadBoard(final Long gameId, final Turn turn) {
        return null;
    }

    @Override
    public void deleteBoard(final long gameId) {

    }
}
