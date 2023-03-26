package chess.dao;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface BoardDao {

    void saveBoard(final Board board, final long gameId);

    void updatePiecePosition(final Position from, final Position to);

    Board loadBoard(final Long gameId);

    void deleteGame();
}
