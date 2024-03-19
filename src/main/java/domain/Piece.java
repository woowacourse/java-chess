package domain;

public interface Piece {
    PieceMoveResult move(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard);
}
