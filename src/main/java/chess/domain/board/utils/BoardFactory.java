package chess.domain.board.utils;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public abstract class BoardFactory {
    public abstract Map<Position, Piece> create();
}
