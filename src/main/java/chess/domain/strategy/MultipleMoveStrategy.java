package chess.domain.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.OutOfBoardRangeException;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleMoveStrategy extends DefaultMoveStrategy {
    @Override
    public List<Position> getPossiblePositions(List<Piece> board, Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            try {
                Position nextPosition = direction.move(piece.getPosition());

                while (isBlankPieceInsideBoard(board, nextPosition)) {
                    possiblePositions.add(nextPosition);
                    nextPosition = direction.move(nextPosition);
                }

                if (isOpponentPieceInsideBoard(board, piece, nextPosition)) {
                    possiblePositions.add(nextPosition);
                }
            } catch (OutOfBoardRangeException ignored) {
            }
        }
        return possiblePositions;
    }
}
