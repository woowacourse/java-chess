package chess.domain.piece.implementation.Strategy;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;

class PawnMoveStrategy {

    private MovingDirection moveDirection;
    private Rank startRank;


    PawnMoveStrategy(MovingDirection moveDirection, Rank startRank) {
        this.moveDirection = moveDirection;
        this.startRank = startRank;
    }

    List<Position> getMovablePositionsWithoutObstacles(Position source) {
        List<Position> positions = new ArrayList<>();
        if (source.canMoveBy(moveDirection)) {
            positions.add(source.moveByDirection(moveDirection));
            putIfStartPosition(source, positions);
        }
        return positions;
    }

    private void putIfStartPosition(Position source, List<Position> positions) {
        if (isFirstMove(source)) {
            positions.add(source.moveByDirection(moveDirection).moveByDirection(moveDirection));
        }
    }

    List<Position> getMovablePositions(Position source, BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        if (source.canMoveBy(moveDirection)) {
            Position startPosition = source.moveByDirection(moveDirection);
            if (boardSituation.canMove(startPosition)) {
                positions.add(startPosition);
                addIfStartPosition(source, boardSituation, positions);
            }
        }
        return positions;
    }

    private void addIfStartPosition(Position source, BoardSituation boardSituation, List<Position> positions) {
        if (isFirstMove(source)) {
            Position position = source.moveByDirection(moveDirection).moveByDirection(moveDirection);
            if (boardSituation.canMove(position)) {
                positions.add(position);
            }
        }
    }

    private boolean isFirstMove(Position source) {
        return source.isSameRank(startRank);
    }
}
