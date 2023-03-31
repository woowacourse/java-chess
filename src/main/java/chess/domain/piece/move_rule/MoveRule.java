package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public interface MoveRule {

    List<Position> move(Position currentPosition, Position nextPosition);

    PieceType pieceType();

    boolean isPawnMove();

    boolean isKingMove();
}
