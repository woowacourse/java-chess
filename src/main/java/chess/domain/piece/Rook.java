package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.LEFT;
import static chess.domain.position.Direction.RIGHT;
import static chess.domain.position.Direction.TOP;

import chess.domain.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(RIGHT, LEFT, TOP, BOTTOM);
    private static final String NOTATION = "R";

    public Rook(Color color) {
        super(color);
    }

    public boolean canMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        return DIRECTIONS.contains(direction) && existPieceInPath(board, sourcePosition, targetPosition, direction);
    }

    private boolean existPieceInPath(List<List<Piece>> board, Position sourcePosition, Position targetPosition,
                                     Direction direction) {
        Position currentPosition = sourcePosition;
        while (!currentPosition.equals(targetPosition)) {
            currentPosition = currentPosition.add(direction);
            Piece currentPiece = findPiece(board, currentPosition);
            if (!currentPiece.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Piece findPiece(List<List<Piece>> board, Position sourcePosition) {
        int rankIndex = sourcePosition.getRankIndex();
        int fileIndex = sourcePosition.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}