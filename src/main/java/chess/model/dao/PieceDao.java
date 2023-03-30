package chess.model.dao;

import chess.model.domain.board.Board;
import chess.model.domain.board.Turn;
import chess.model.domain.position.Position;

public interface PieceDao {

    void saveBoard(final Board board, final long gameId);

    void updatePiecePosition(final Position from, final Position to, final long gameId);

    Board loadBoard(final Long gameId, final Turn turn);

    void deleteBoard(long gameId);
}
