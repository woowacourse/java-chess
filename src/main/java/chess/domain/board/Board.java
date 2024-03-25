package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.NoPiece;
import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board generatedBy(BoardGenerator boardGenerator) {
        return new Board(boardGenerator.generate());
    }

    public void move(Position source, Position target, Color color) {
        Piece piece = board.get(source);
        validateMove(source, target, color);
        board.put(source, new NoPiece(Color.NO_COLOR));
        board.put(target, piece);
    }

    private void validateMove(Position source, Position target, Color color) {
        validateNoPieceAtSource(source);
        validateTurn(source, color);
        validateMoveToSamePosition(source, target);
        validateOwnPieceAtTarget(source, target);
        validatePieceRule(source, target);
        validateRoute(source, target);
        validatePawnMove(source, target);
    }

    private void validateNoPieceAtSource(Position source) {
        if (!findPieceAt(source).exists()) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void validateTurn(Position source, Color color) {
        if (!findPieceAt(source).hasColorOf(color)) {
            throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
        }
    }

    private void validateMoveToSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("출발점과 도착점은 같을 수 없습니다.");
        }
    }

    private void validateOwnPieceAtTarget(Position source, Position target) {
        if (findPieceAt(target).exists() && (findPieceColorAt(source) == findPieceColorAt(target))) {
            throw new IllegalArgumentException("한 칸에 말이 2개 존재할 수 없습니다.");
        }
    }

    private void validatePieceRule(Position source, Position target) {
        if (findPieceAt(source).canMove(source, target)) {
            return;
        }
        throw new IllegalArgumentException("말의 규칙에 맞지 않는 이동입니다.");
    }

    private void validateRoute(Position source, Position target) {
        if (isStraightMove(source, target) || isDiagonalMove(source, target)) {
            validatePieceExistOnRoute(source, target);
        }
    }

    private void validatePieceExistOnRoute(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        Position currentPosition = source.nextPosition(direction);
        while (!currentPosition.equals(target)) {
            validatePieceExistAt(currentPosition);
            currentPosition = currentPosition.nextPosition(direction);
        }
    }

    private void validatePieceExistAt(Position position) {
        if (findPieceAt(position).exists()) {
            throw new IllegalArgumentException("경로에 말이 있으면 움직일 수 없습니다.");
        }
    }

    private void validatePawnMove(Position source, Position target) {
        if (findPieceAt(source).isPawn()) {
            validatePawnStraightCapture(source, target);
            validatePawnDiagonalMove(source, target);
        }
    }

    private void validatePawnStraightCapture(Position source, Position target) {
        if (isStraightMove(source, target) && findPieceAt(target).exists()) {
            throw new IllegalArgumentException("직진으로 잡을 수 없습니다.");
        }
    }

    private void validatePawnDiagonalMove(Position source, Position target) {
        if (isDiagonalMove(source, target) && pieceDoesNotExistAt(target)) {
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

    private boolean pieceDoesNotExistAt(Position position) {
        return !findPieceAt(position).exists();
    }

    public Piece findPieceAt(Position position) {
        return board.get(position);
    }

    private Color findPieceColorAt(Position position) {
        if (pieceDoesNotExistAt(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        if (board.get(position).isWhite()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
