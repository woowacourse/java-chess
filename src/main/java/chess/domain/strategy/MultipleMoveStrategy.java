package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class MultipleMoveStrategy implements MoveStrategy {
    @Override
    public List<Position> possiblePositions(Board board, Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : piece.directions()) {

            Piece nextPiece = piece;
            while (nextPiece.isNextPositionValid(direction)) {
                Position nextPosition = nextPiece.getPosition().moveBy(direction);
                nextPiece = board.findPieceBy(nextPosition);

                if (nextPiece.isBlank()) {
                    possiblePositions.add(nextPosition);
                    nextPiece = board.findPieceBy(nextPosition);
                }

                else if (nextPiece.isOtherTeam(piece)) {
                    possiblePositions.add(nextPosition);
                    break;
                }

                else if (nextPiece.isSameTeam(piece)) {
                    break;
                }
            }
        }
        return possiblePositions;
    }
}
