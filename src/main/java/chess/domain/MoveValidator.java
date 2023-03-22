package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveValidator {

    public static void validate(Map<Position, Piece> board, Position from, Position to) {
        validateEmpty(board, from);
        validateObstacle(board, from, to);
    }

    private static void validateEmpty(Map<Position, Piece> board, Position source) {
        if (isEmpty(board, source)) {
            throw new IllegalArgumentException("source 위치에 조작할 수 있는 말이 없습니다.");
        }
    }

    private static boolean isEmpty(Map<Position, Piece> board, Position source) {
        return board.get(source).isEmpty();
    }

    private static void validateObstacle(Map<Position, Piece> board, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isPieceType(PieceType.KNIGHT) && hasObstacle(board, source, target)) {
            throw new IllegalArgumentException("말이 target 위치로 움직일 수 없습니다.");
        }
    }

    private static boolean hasObstacle(Map<Position, Piece> board, Position source, Position target) {
        RelativePosition relativePosition = RelativePosition.of(source, target);
        RelativePosition unitPosition = relativePosition.toUnit();
        Position currentPosition = source.move(unitPosition);

        List<Position> positions = new ArrayList<>();
        while (!currentPosition.equals(target)) {
            positions.add(currentPosition);
            currentPosition = currentPosition.move(unitPosition);
        }

        return positions.stream()
                .anyMatch(position -> !isEmpty(board, position));
    }
}
