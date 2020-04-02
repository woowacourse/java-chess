package chess.domain.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.OutOfBoardRangeException;

import java.util.ArrayList;
import java.util.List;

public abstract class PawnMoveStrategy extends DefaultMoveStrategy {
    @Override
    public List<Position> getPossiblePositions(List<Piece> board, Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            try {
                Position nextPosition = piece.getPosition().move(direction);

                if (direction == getForwardDirection()) {
                    if (isBlankPieceInsideBoard(board, nextPosition)) {
                        possiblePositions.add(nextPosition);
                    }
                    if (isFirstMove(piece)) {
                        possiblePositions.add(nextPosition.move(direction));
                    }
                    continue;
                }

                if (isOpponentPieceInsideBoard(board, piece, nextPosition)) {
                    possiblePositions.add(nextPosition);
                }
            } catch (OutOfBoardRangeException ignored) {
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
