package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.OutOfBoardRangeException;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleMoveStrategy extends DefaultMoveStrategy {
    @Override
    public List<Position> getPossiblePositions(final Board board, final Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            try {
                Position nextPosition = piece.getPosition().moveBy(direction);
                Piece nextPiece = board.findPieceBy(nextPosition);

                if (isBlankPieceInsideBoard(nextPiece)) {
                    possiblePositions.add(nextPosition);
                }

                if (isOpponentPieceInsideBoard(piece, nextPiece)) {
                    possiblePositions.add(nextPosition);
                }
            } catch (OutOfBoardRangeException ignored) {
            }
        }
        return possiblePositions;
    }
}
