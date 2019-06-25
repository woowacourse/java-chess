package chess.domain.factory;

import chess.domain.PieceType;
import chess.domain.piece.ChessPiece;

@FunctionalInterface
public interface AbstractChessPieceFactory {
    ChessPiece create(PieceType type);
}
