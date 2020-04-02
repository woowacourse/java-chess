package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> getPossiblePositions(final Board board, final Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {

            if (piece.isNextPositionValid(direction)) {
                Position nextPosition = piece.getPosition().moveBy(direction);
                Piece nextPiece = board.findPieceBy(nextPosition);

                if (nextPiece.isBlank()) {
                    possiblePositions.add(nextPosition);
                }

                else if (nextPiece.isOtherTeam(piece)) {
                    possiblePositions.add(nextPosition);
                }
            }
        }
        return possiblePositions;
    }
}
