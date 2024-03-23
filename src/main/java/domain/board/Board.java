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

    public Board move(Position source, Position target, Turn turn) {
        Piece piece = board.get(source);
        validate(source, target, turn);
        board.remove(source);
        board.put(target, piece);
        return new Board(board);
    }

    private void validate(Position source, Position target, Turn turn) {
        validatePieceExistToMove(source);
        validateIsOwnTurn(source, turn);
        validateDifferentPosition(source, target);
        validateOwnPieceNotExistAtTarget(source, target);
        validatePieceCanMove(source, target);
        validateWhenStraightOrDiagonalMove(source, target);
        validateWhenPieceIsPawn(source, target);
    }
    
    private void validatePieceExistToMove(Position source) {
        if (isNoPieceAt(source)) {
            throw new IllegalArgumentException("source 위치에 말이 없습니다.");
        }
    }

    private void validateIsOwnTurn(Position source, Turn turn) {
        Piece piece = board.get(source);
        if (piece.hasColorOf(turn.getColor())) {
            return;
        }
        throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
    }

    private void validateDifferentPosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void validateOwnPieceNotExistAtTarget(Position source, Position target) {
        if (isPieceAt(target) && (findPieceColorAt(source) == findPieceColorAt(target))) {
            throw new IllegalArgumentException("한 칸에 말이 2개 존재할 수 없습니다.");
        }
    }

    private void validatePieceCanMove(Position source, Position target) {
        Piece piece = board.get(source);
        if (piece.canMove(source, target)) {
            return;
        }
        throw new IllegalArgumentException("말의 규칙에 맞지 않는 이동입니다.");
    }

    private void validateWhenStraightOrDiagonalMove(Position source, Position target) {
        if (isStraightMove(source, target) || isDiagonalMove(source, target)) {
            validatePieceNotExistOnRoute(source, target);
        }
    }

    private void validatePieceNotExistOnRoute(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        Position currentPosition = source.nextPosition(direction);
        while (!currentPosition.equals(target)) {
            validatePieceNotExistAt(currentPosition);
            currentPosition = currentPosition.nextPosition(direction);
        }
    }

    private void validatePieceNotExistAt(Position middlePosition) {
        if (isPieceAt(middlePosition)) {
            throw new IllegalArgumentException("경로에 말이 있으면 움직일 수 없습니다.");
        }
    }

    private void validateWhenPieceIsPawn(Position source, Position target) {
        Piece piece = board.get(source);
        if (piece instanceof Pawn) {
            validatePawnStraightMove(source, target);
            validatePawnDiagonalCapture(source, target);
        }
    }

    private void validatePawnStraightMove(Position source, Position target) {
        if (isStraightMove(source, target) && isPieceAt(target)) {
            throw new IllegalArgumentException("직진으로 잡을 수 없습니다.");
        }
    }

    private void validatePawnDiagonalCapture(Position source, Position target) {
        if (isDiagonalMove(source, target) && isNoPieceAt(target)) {
            throw new IllegalArgumentException("대각선 방향에 상대방 말이 없으면 움직일 수 없습니다.");
        }
    }

    private boolean isStraightMove(Position source, Position target) {
        return source.isOnSameRankAs(target)
                || source.isOnSameFileAs(target);
    }

    private boolean isDiagonalMove(Position source, Position target) {
        return source.isOnSameDiagonalAs(target);
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
