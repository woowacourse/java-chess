package chess.domain;

import chess.domain.piece.NoneEmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.Map;

public class MoveValidator {

    public static void validate(Map<Position, Piece> board, Position source, Position target) {
        validateEmpty(board, source);
        validateSameTeam(board, source, target);
        validateInvalidDirection(board, source, target);
        validateObstacle(board, source, target);
        validateDiagonalImpossible(board, source, target);
    }

    private static void validateEmpty(Map<Position, Piece> board, Position source) {
        if (isEmpty(board, source)) {
            throw new IllegalArgumentException("source 위치에 조작할 수 있는 말이 없습니다.");
        }
    }

    private static boolean isEmpty(Map<Position, Piece> board, Position source) {
        return board.get(source).isEmpty();
    }

    private static void validateSameTeam(Map<Position, Piece> board, Position source, Position target) {
        if (isSameTeam(board, source, target)) {
            throw new IllegalArgumentException("target 위치에 같은 팀의 말이 존재합니다.");
        }
    }

    private static boolean isSameTeam(Map<Position, Piece> board, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (sourcePiece.isBlack() && targetPiece.isBlack()) {
            return true;
        }
        return sourcePiece.isWhite() && targetPiece.isWhite();
    }

    private static void validateInvalidDirection(Map<Position, Piece> board, Position source, Position target) {
        if (isInvalidDirection(board, source, target)) {
            throw new IllegalArgumentException("말이 target 위치로 움직일 수 없습니다.");
        }
    }

    private static boolean isInvalidDirection(Map<Position, Piece> board, Position source, Position target) {
        RelativePosition relativePosition = RelativePosition.of(source, target);
        Piece piece = board.get(source);
        return !piece.isMobile(relativePosition);
    }

    private static void validateObstacle(Map<Position, Piece> board, Position source, Position target) {
        NoneEmptyPiece sourcePiece = (NoneEmptyPiece) board.get(source);
        if (!sourcePiece.isKnight() && hasObstacle(board, source, target)) {
            throw new IllegalArgumentException("말이 target 위치로 움직일 수 없습니다.");
        }
    }

    private static boolean hasObstacle(Map<Position, Piece> board, Position source, Position target) {
        RelativePosition relativePosition = RelativePosition.of(source, target);
        RelativePosition unitPosition = relativePosition.toUnit();
        Position currentPosition = source.move(unitPosition);
        while (!currentPosition.equals(target)) {
            if (!isEmpty(board, currentPosition)) {
                return true;
            }
            currentPosition = currentPosition.move(unitPosition);
        }
        return false;
    }

    private static void validateDiagonalImpossible(Map<Position, Piece> board, Position source, Position target) {
        NoneEmptyPiece sourcePiece = (NoneEmptyPiece) board.get(source);
        RelativePosition relativePosition = RelativePosition.of(source, target);
        if (sourcePiece.isPawn() && relativePosition.isDiagonal() && isEmpty(board, target)) {
            throw new IllegalArgumentException("말이 target 위치로 움직일 수 없습니다.");
        }
    }
}
