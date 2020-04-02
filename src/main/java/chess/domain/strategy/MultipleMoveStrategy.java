package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> getPossiblePositions(Board board, Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            Piece tempPiece = piece;
            while (tempPiece.isNextPositionValid(direction)) {
                Position nextPosition = tempPiece.getPosition().moveBy(direction);
                tempPiece = board.findPieceBy(nextPosition);

                if (tempPiece.isBlank()) {
                    possiblePositions.add(nextPosition);
                    tempPiece = board.findPieceBy(nextPosition);
                }

                else if (tempPiece.isOtherTeam(piece)) {
                    possiblePositions.add(nextPosition);
                    break;
                }

                else if (tempPiece.isSameTeam(piece)) {
                    break;
                }
            }
        }
        return possiblePositions;
    }
}
