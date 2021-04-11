package chess.domain.piece.moving;

import chess.domain.Position;
import chess.domain.game.ImpossibleMoveException;

import java.util.List;

public interface PieceMoving {

    void updateMovablePositions(List<Position> existPiecePositions, List<Position> enemiesPositions);

    void move(Position targetPosition) throws ImpossibleMoveException;

    Position currentPosition();

    List<Position> movablePositions();

    boolean notMoved();

    boolean isSamePosition(Position position);

    int row();
}

