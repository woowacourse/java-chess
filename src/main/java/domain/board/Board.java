package domain.board;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    private Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public static Board generatedBy(BoardGenerator boardGenerator) {
        return new Board(boardGenerator.generate());
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece piece = squares.get(sourcePosition);
        validate(sourcePosition, targetPosition);
        squares.remove(sourcePosition);
        squares.put(targetPosition, piece);
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        validateSamePosition(sourcePosition, targetPosition);
        validateNoPieceToMove(sourcePosition);
        validateOwnPieceExistAtTargetPosition(sourcePosition, targetPosition);
        validatePieceCanMove(sourcePosition, targetPosition);
        validateWhenStraightOrDiagonalMove(sourcePosition, targetPosition);
        validateWhenPieceIsPawn(sourcePosition, targetPosition);
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void validateNoPieceToMove(Position sourcePosition) {
        if (isNoPieceAt(sourcePosition)) {
            throw new IllegalArgumentException("source 위치에 말이 없습니다.");
        }
    }

    private void validateOwnPieceExistAtTargetPosition(Position sourcePosition, Position targetPosition) {
        if (isPieceAt(targetPosition) && (findPieceColorAt(sourcePosition) == findPieceColorAt(targetPosition))) {
            throw new IllegalArgumentException("한 칸에 말이 2개 존재할 수 없습니다.");
        }
    }

    private void validatePieceCanMove(Position sourcePosition, Position targetPosition) {
        Piece piece = squares.get(sourcePosition);
        if (piece.canMove(sourcePosition, targetPosition)) {
            return;
        }
        throw new IllegalArgumentException("말의 규칙에 맞지 않는 이동입니다.");
    }

    private void validateWhenStraightOrDiagonalMove(Position sourcePosition, Position targetPosition) {
        if (isStraightMove(sourcePosition, targetPosition) || isDiagonalMove(sourcePosition, targetPosition)) {
            validatePieceExistOnRoute(sourcePosition, targetPosition);
        }
    }

    private void validatePieceExistOnRoute(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        Position currentPosition = sourcePosition.nextPosition(direction);
        while (!currentPosition.equals(targetPosition)) {
            validatePieceExistAt(currentPosition);
            currentPosition = currentPosition.nextPosition(direction);
        }
    }

    private void validatePieceExistAt(Position middlePosition) {
        if (isPieceAt(middlePosition)) {
            throw new IllegalArgumentException("경로에 말이 있으면 움직일 수 없습니다.");
        }
    }

    private void validateWhenPieceIsPawn(Position sourcePosition, Position targetPosition) {
        Piece piece = squares.get(sourcePosition);
        if (piece instanceof Pawn) {
            validatePawnStraightCapture(sourcePosition, targetPosition);
            validatePawnDiagonalMove(sourcePosition, targetPosition);
        }
    }

    private void validatePawnStraightCapture(Position sourcePosition, Position targetPosition) {
        if (isStraightMove(sourcePosition, targetPosition) && isPieceAt(targetPosition)) {
            throw new IllegalArgumentException("직진으로 잡을 수 없습니다.");
        }
    }

    private void validatePawnDiagonalMove(Position sourcePosition, Position targetPosition) {
        if (isDiagonalMove(sourcePosition, targetPosition) && isNoPieceAt(targetPosition)) {
            throw new IllegalArgumentException("대각선 방향에 상대방 말이 없으면 움직일 수 없습니다.");
        }
    }

    private boolean isStraightMove(Position sourcePosition, Position targetPosition) {
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        return rankDifference == 0 || fileDifference == 0;
    }

    private boolean isDiagonalMove(Position sourcePosition, Position targetPosition) {
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    private boolean isPieceAt(Position position) {
        return squares.containsKey(position);
    }

    private boolean isNoPieceAt(Position position) {
        return !squares.containsKey(position);
    }

    private Color findPieceColorAt(Position position) {
        if (isNoPieceAt(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        if (squares.get(position).isWhite()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Piece findPieceAt(Position position) {
        if (isNoPieceAt(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        return squares.get(position);
    }
}
