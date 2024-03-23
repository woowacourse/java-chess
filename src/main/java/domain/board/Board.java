package domain.board;

import domain.game.Turn;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board generatedBy(BoardGenerator boardGenerator) {
        return new Board(boardGenerator.generate());
    }

    public Board move(Position sourcePosition, Position targetPosition, Turn turn) {
        Piece piece = board.get(sourcePosition);
        validate(sourcePosition, targetPosition, turn);
        board.remove(sourcePosition);
        board.put(targetPosition, piece);
        return new Board(board);
    }

    private void validate(Position sourcePosition, Position targetPosition, Turn turn) {
        validateNoPieceToMove(sourcePosition);
        validateTurn(sourcePosition, turn);
        validateSamePosition(sourcePosition, targetPosition);
        validateOwnPieceExistAtTargetPosition(sourcePosition, targetPosition);
        validatePieceCanMove(sourcePosition, targetPosition);
        validateWhenStraightOrDiagonalMove(sourcePosition, targetPosition);
        validateWhenPieceIsPawn(sourcePosition, targetPosition);
    }

    private void validateNoPieceToMove(Position sourcePosition) {
        if (isNoPieceAt(sourcePosition)) {
            throw new IllegalArgumentException("source 위치에 말이 없습니다.");
        }
    }

    private void validateTurn(Position sourcePosition, Turn turn) {
        Piece piece = board.get(sourcePosition);
        if (piece.hasColorOf(turn.getColor())) {
            return;
        }
        throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void validateOwnPieceExistAtTargetPosition(Position sourcePosition, Position targetPosition) {
        if (isPieceAt(targetPosition) && (findPieceColorAt(sourcePosition) == findPieceColorAt(targetPosition))) {
            throw new IllegalArgumentException("한 칸에 말이 2개 존재할 수 없습니다.");
        }
    }

    private void validatePieceCanMove(Position sourcePosition, Position targetPosition) {
        Piece piece = board.get(sourcePosition);
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
        Piece piece = board.get(sourcePosition);
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
        return sourcePosition.isOnSameRankAs(targetPosition)
                || sourcePosition.isOnSameFileAs(targetPosition);
    }

    private boolean isDiagonalMove(Position sourcePosition, Position targetPosition) {
        return sourcePosition.isOnSameDiagonalAs(targetPosition);
    }

    private boolean isPieceAt(Position position) {
        return board.containsKey(position);
    }

    private boolean isNoPieceAt(Position position) {
        return !board.containsKey(position);
    }

    private Color findPieceColorAt(Position position) {
        if (isNoPieceAt(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        if (board.get(position).isWhite()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Piece findPieceAt(Position position) {
        if (isNoPieceAt(position)) {
            return null;
        }
        return board.get(position);
    }
}
