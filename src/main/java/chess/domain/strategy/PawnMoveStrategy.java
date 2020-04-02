package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class PawnMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> getPossiblePositions(Board board, Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            if (piece.isNextPositionValid(direction)) {
                Position nextPosition = piece.getPosition().moveBy(direction);
                Piece nextPiece = board.findPieceBy(nextPosition);

                if (direction == getForwardDirection()) {
                    if (nextPiece.isBlank()) {
                        possiblePositions.add(nextPosition);
                    }
                    if (isFirstMove(piece)) {
                        possiblePositions.add(nextPosition.moveBy(direction));
                    }
                    continue;
                }

                if (piece.isOtherTeam(nextPiece)) {
                    possiblePositions.add(nextPosition);
                }
            }
        }
        return possiblePositions;
    }


    private boolean isFirstMove(Piece piece) {
        return piece.getRow() == getInitialPawnRow();
    }

    protected abstract int getInitialPawnRow();

    protected abstract Direction getForwardDirection();
}
