package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;
    private final PawnMovementRule pawnMovementRule;

    public ChessBoard(final Map<Position, Piece> chessBoard, PawnMovementRule pawnMovementRule) {
        this.chessBoard = chessBoard;
        this.pawnMovementRule = pawnMovementRule;
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }

    public void move(final Position sourcePosition, final Position targetPosition) {
        validateCanMove(sourcePosition, targetPosition);

        Piece sourcePiece = chessBoard.get(sourcePosition);
        chessBoard.put(targetPosition, sourcePiece);
        chessBoard.put(sourcePosition, EmptyPiece.of());
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition) {
        validateNotSourceItSelf(sourcePosition, targetPosition);
        validateSourcePositionNotEmpty(sourcePosition);
        validateTargetNotAlly(sourcePosition, targetPosition);

        if (chessBoard.get(sourcePosition).isPawn()) {
            validatePawnCanMove(sourcePosition, targetPosition);
            return;
        }
        Piece sourcePiece = chessBoard.get(sourcePosition);
        Direction direction = sourcePosition.calculateDirection(targetPosition);
        validateValidDirection(sourcePiece, direction);
        Position reachedPosition = moveUntilTargetOrMeetSomeThing(sourcePosition, targetPosition, direction);
        validateReachedTarget(targetPosition, reachedPosition);
    }

    private void validateNotSourceItSelf(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("제자리로 움직일 수 없습니다.");
        }
    }

    private void validateTargetNotAlly(Position source, Position target) {
        if (chessBoard.get(source).isAlly(chessBoard.get(target))) {
            throw new IllegalArgumentException("이동하려는 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateSourcePositionNotEmpty(Position position) {
        if (chessBoard.get(position).equals(EmptyPiece.of())) {
            throw new IllegalArgumentException("비어있는 곳에서는 기물을 움직일 수 없습니다.");
        }
    }

    private void validatePawnCanMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.calculateDirection(targetPosition);
        Piece sourcePiece = chessBoard.get(sourcePosition);
        Piece targetPiece = chessBoard.get(targetPosition);

        if (sourcePiece.canMoveInTargetDirection(direction)) {
            validatePawnCanMoveForward(sourcePosition, targetPosition, direction);
            return;
        }

        validatePawnCanMoveDiagonal(direction, sourcePiece, targetPiece);
    }

    private void validatePawnCanMoveForward(Position sourcePosition, Position targetPosition, Direction direction) {
        boolean canReachTargetWhenMoveForward = pawnMovementRule.canReachTargetWhenMoveForward(sourcePosition, targetPosition, direction);

        if (!(canReachTargetWhenMoveForward)) {
            throw new IllegalArgumentException("폰은 해당 위치에 도달할 수 없습니다.");
        }
    }

    private void validatePawnCanMoveDiagonal(Direction direction, Piece sourcePiece, Piece targetPiece) {
        boolean canMoveTowardDiagonal = pawnMovementRule.canMoveTowardDiagonal(sourcePiece, targetPiece, direction);

        if (!canMoveTowardDiagonal) {
            throw new IllegalArgumentException("폰은 해당 위치에 도달할 수 없습니다.");
        }
    }

    private void validateValidDirection(final Piece piece, final Direction direction) {
        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("선택한 기물이 이동할 수 없는 방향입니다.");
        }
    }

    private Position moveUntilTargetOrMeetSomeThing(Position sourcePosition, Position targetPosition, Direction direction) {
        Position nextPosition = sourcePosition.moveTowardDirection(direction);
        while (chessBoard.get(sourcePosition).canMoveMoreThenOnce() &&
                !nextPosition.equals(targetPosition) &&
                chessBoard.get(nextPosition) == EmptyPiece.of()) {
            nextPosition = nextPosition.moveTowardDirection(direction);
        }

        return nextPosition;
    }

    private void validateReachedTarget(Position targetPosition, Position nextPosition) {
        if (!nextPosition.equals(targetPosition)) {
            throw new IllegalArgumentException("선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }
    }
}
