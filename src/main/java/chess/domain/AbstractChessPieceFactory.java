package chess.domain;

@FunctionalInterface
public interface AbstractChessPieceFactory {
    ChessPiece create(PieceType type);
}
