package chess.domain.board.factory;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public abstract class BoardFactory {
    public abstract Map<Position, Piece> create();
}
