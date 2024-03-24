package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.dto.SquareDifferent;

public class BlockedPathCheckStrategy implements LegalMoveCheckStrategy {

    @Override
    public boolean check(Square source, Square destination, Board board) {
        SquareDifferent diff = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(diff);

        Square candidate = source;
        while (!candidate.equals(destination)) {
            if (isBlocked(source, board, candidate)) {
                return false;
            }

            candidate = direction.nextSquare(candidate);
        }

        return true;
    }

    private boolean isBlocked(Square source, Board board, Square candidate) {
        return !source.equals(candidate) && board.findPieceBySquare(candidate).isNotEmpty();
    }
}
