package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class SingleMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> possiblePositions(final Board board, final Piece piece, final Position position) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : piece.getDirections()) {
            if (position.isNextPositionValidForward(direction)) {
                Position nextPosition = position.moveBy(direction);
                Piece nextPiece = board.findBy(nextPosition);

                if (nextPiece.isBlank()) {
                    possiblePositions.add(nextPosition);
                } else if (piece.isOtherTeam(nextPiece)) {
                    possiblePositions.add(nextPosition);
                }
            }
        }
        return possiblePositions;
    }
}
