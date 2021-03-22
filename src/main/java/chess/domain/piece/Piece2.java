package chess.domain.piece;

import chess.domain.board.Paths2;
import chess.domain.position.Position2;

public interface Piece2 {

    Paths2 possiblePaths(Position2 currentPosition);

    boolean isColor(PieceColor color);

    boolean isNothing();
}
