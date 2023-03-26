package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.position.Position;

public interface BoardDao {

    void saveBoard(final Board board, final long gameId);

    void updatePiecePosition(final Position from, final Position to, final long gameId);

    Board loadBoard(final Long gameId, final Turn turn);

    void deleteGame();
}
