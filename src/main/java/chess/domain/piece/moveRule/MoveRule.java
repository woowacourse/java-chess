package chess.domain.piece.moveRule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public interface MoveRule {

    List<Position> move(Position currentPosition, Position nextPosition);

    PieceType pieceType();

    boolean isPawnMove();
}
