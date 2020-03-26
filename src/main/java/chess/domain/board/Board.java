package chess.domain.board;

import chess.domain.Piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface Board {
    Board movePiece(Position from, Position to);
    Piece getPiece(Position position);

}
