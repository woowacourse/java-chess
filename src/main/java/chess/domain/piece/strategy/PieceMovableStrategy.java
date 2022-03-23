package chess.domain.piece.strategy;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface PieceMovableStrategy {

    boolean isMovable(Position start, Position target, Map<Position, Piece> chessBoard);
}
