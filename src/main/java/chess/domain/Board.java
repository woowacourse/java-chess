package chess.domain;

import chess.domain.Piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public interface Board {
    Piece getPiece(Position position);
    List<Piece> getPieces(List<Position> positions);
}
