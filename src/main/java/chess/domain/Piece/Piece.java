package chess.domain.Piece;


import chess.domain.Position;

public interface Piece {
    Piece move(Position to, Piece exPiece);
}
