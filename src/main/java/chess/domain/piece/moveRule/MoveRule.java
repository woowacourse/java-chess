package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;

public interface MoveRule {
    void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board);

    PieceType pieceType();
}
