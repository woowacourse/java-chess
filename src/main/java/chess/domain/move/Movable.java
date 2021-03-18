package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public interface Movable {
    void move(final Position position, final ChessBoard chessBoard);
}
