package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.position.Target;

public interface Movable {
    void move(final Target target, final ChessBoard chessBoard);
}
