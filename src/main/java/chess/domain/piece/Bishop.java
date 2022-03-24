package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM_LEFT;
import static chess.domain.position.Direction.BOTTOM_RIGHT;
import static chess.domain.position.Direction.TOP_LEFT;
import static chess.domain.position.Direction.TOP_RIGHT;

import chess.domain.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT);
    private static final String NOTATION = "B";

    public Bishop(Color color) {
        super(color);
    }

    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        validateDirection(direction);

        Position currentPosition = sourcePosition;
        while (!currentPosition.equals(targetPosition)) {
            currentPosition = currentPosition.add(direction);
            Piece currentPiece = findPiece(board, currentPosition);
            validateExistPiece(currentPiece);
        }
    }

    private void validateDirection(Direction direction) {
        if (!DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateExistPiece(Piece currentPiece) {
        if (!currentPiece.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
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
