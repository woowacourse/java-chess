package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class QueenMove extends Move {

    public boolean canMove(final Position source, final Position target, final ChessBoard chessBoard) {
        final Set<Position> allPositions = getAllPositions(source, chessBoard, Direction.getAllDirections());
        return allPositions.contains(target);
    }

    private Set<Position> getAllPositions(final Position source, final ChessBoard chessBoard,
                                          final List<Direction> allDirections) {
        final Set<Position> positions = new HashSet<>();
        for (Direction direction : allDirections) {
            Position possiblePosition = source;
            for (int i = 0; i < ChessBoard.FILE_SIZE; i++) {
                possiblePosition = move(possiblePosition, direction);
                if (!chessBoard.contains(possiblePosition)) {
                    positions.add(possiblePosition);
                }
            }
        }
        return positions;
    }
}
