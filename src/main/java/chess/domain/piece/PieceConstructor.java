package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

@FunctionalInterface
public interface PieceConstructor<P extends Piece> {
    P create(Color color, Position position);
}
