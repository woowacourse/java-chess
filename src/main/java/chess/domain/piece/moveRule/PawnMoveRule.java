package chess.domain.piece.moveRule;

import chess.domain.Direction;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;

public class PawnMoveRule extends UnJumpableMoveRule {
    public static final int TWO_SQUARE = 2;
    public static final int ONE_SQUARE = 1;
    private static final Rank WHITE_PAWN_INIT_RANK = Rank.RANK2;
    private static final Rank BLACK_PAWN_INIT_RANK = Rank.RANK7;
    private final Direction direction;

    private PawnMoveRule(Direction direction) {
        this.direction = direction;
    }

    public static PawnMoveRule of(Color color) {
        if (color == Color.BLACK) {
            return new PawnMoveRule(Direction.MINUS);
        }
        return new PawnMoveRule(Direction.PLUS);
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateRoute(board, currentPosition.getRoute(nextPosition));
        if (isInitPawn(currentPosition) && isForwardDistanceMove(currentPosition, nextPosition, TWO_SQUARE)) {
            moveForward(currentPosition, nextPosition, board);
            return;
        }
        if (isForwardDistanceMove(currentPosition, nextPosition, ONE_SQUARE)) {
            moveForward(currentPosition, nextPosition, board);
            return;
        }
        if (isDiagonalMove(currentPosition, nextPosition)) {
            moveDiagonal(currentPosition, nextPosition, board);
        }
    }

    private boolean isInitPawn(Position currentPosition) {
        if (direction.equals(Direction.PLUS) && currentPosition.isSameRank(WHITE_PAWN_INIT_RANK)) {
            return true;
        }
        return direction.equals(Direction.MINUS) && currentPosition.isSameRank(BLACK_PAWN_INIT_RANK);
    }

    private boolean isForwardDistanceMove(Position currentPosition, Position nextPosition, int distance) {
        Position forwardPosition = currentPosition.moveRank(direction, distance);
        return forwardPosition.equals(nextPosition);
    }

    private boolean isDiagonalMove(Position currentPosition, Position nextPosition) {
        Position leftDiagonal = currentPosition.move(Direction.MINUS, direction);
        Position rightDiagonal = currentPosition.move(Direction.PLUS, direction);
        return leftDiagonal.equals(nextPosition) || rightDiagonal.equals(nextPosition);
    }

    private void moveForward(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (board.containsKey(nextPosition)) {
            throw new IllegalArgumentException("폰의 이동위치에 다른 기물이 있습니다.");
        }
        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void moveDiagonal(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateEmpty(nextPosition, board);
        validateDiagonalNoOpponent(currentPosition, nextPosition, board);
        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateEmpty(Position nextPosition, Map<Position, Piece> board) {
        if (!board.containsKey(nextPosition)) {
            throw new IllegalArgumentException("폰은 기물이 없는 칸으로 대각선 이동할 수 없습니다.");
        }
    }

    private void validateDiagonalNoOpponent(Position currentPosition, Position nextPosition,
                                            Map<Position, Piece> board) {
        Piece nextPiece = board.get(nextPosition);
        Piece curPiece = board.get(currentPosition);
        if (curPiece.isSameColor(nextPiece)) {
            throw new IllegalArgumentException("폰은 아군 기물이 있는 칸으로 대각선 이동하라 수 없습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
}
