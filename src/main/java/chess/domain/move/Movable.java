package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Pieces;
import chess.domain.position.Target;

public interface Movable {
    void move(final Target target, final ChessBoard chessBoard);

    void move2(final Target target, final Pieces pieces);
}
