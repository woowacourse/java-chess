package chess.domain.piece.state;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class Continuous {

    public List<Position> getPositions(Position source, ChessBoard board, Direction direction) {
        List<Position> list = new ArrayList<>();

        Position currentPosition = source;
        Position nextPosition = currentPosition.getNext(direction);

        while (!isBlocked(currentPosition, nextPosition, direction) && !board.isFilled(nextPosition)) {
            list.add(nextPosition);
            currentPosition = nextPosition;
            nextPosition = currentPosition.getNext(direction);
        }

        if (canKill(source, nextPosition, board)) {
            list.add(nextPosition);
        }

        return list;
    }

    private boolean isBlocked(Position current, Position next, Direction direction) {
        if (isUpDownLeftRight(direction)) {
            return current == next;
        }

        return (current.getFile() == next.getFile()) || (current.getRank() == next.getRank());
    }

    private boolean isUpDownLeftRight(Direction direction) {
        return direction == Direction.Up || direction == Direction.Down
            || direction == Direction.Left || direction == Direction.Right;
    }

    private boolean canKill(Position source, Position target, ChessBoard board) {
        return board.isFilled(target) && isDifferentColor(board.getPiece(source), board.getPiece(target));
    }

    private boolean isDifferentColor(Piece sourcePiece, Piece targetPiece) {
        return !sourcePiece.isSameColor(targetPiece);
    }
}
