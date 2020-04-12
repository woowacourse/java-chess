package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class MultipleMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> possiblePositions(Board board, Piece piece, Position position) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : piece.getDirections()) {
            Position currentPosition = position;
            while (currentPosition.isNextPositionValidForward(direction)) {
                Position nextPosition = currentPosition.moveBy(direction);
                Piece nextPiece = board.findBy(nextPosition);

                if (nextPiece.isBlank()) {
                    possiblePositions.add(nextPosition);
                    currentPosition = nextPosition;
                } else if (piece.isOtherTeam(nextPiece)) {
                    possiblePositions.add(nextPosition);
                    break;
                } else if (piece.isSameTeam(nextPiece)) {
                    break;
                }
            }
        }
        return possiblePositions;
    }
}
