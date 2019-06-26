package chess.domain.piece;

import chess.domain.board.Square;

import java.util.Map;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public interface PieceInit {
    Map<Square, Piece> create();
}
