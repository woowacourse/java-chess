package techcourse.fp.chess.dao;

import java.util.Map;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Piece;

public interface PieceDao {

    void save(final Map<Position, Piece> board, final int chessGameId);

    Map<Position, Piece> findByGameId(final int chessGameId);
}
