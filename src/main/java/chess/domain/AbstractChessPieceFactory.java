package chess.domain;

@FunctionalInterface
public interface AbstractChessPieceFactory {
    public ChessPiece create(PieceType type);
}
