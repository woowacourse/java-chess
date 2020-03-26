package chess.domain.board;

import chess.domain.Piece.Piece;
import chess.domain.position.Position;

public interface Board {
    Board movePiece(Position from, Position to);
    Piece getPiece(Position position);

}
