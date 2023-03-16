package chess.domain.piece.moveRule;

import chess.domain.Direction;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public class PawnMoveRule implements MoveRule {
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
        if (isInitPawn(currentPosition) && isForwardTwoMove(currentPosition, nextPosition)) {
            moveForward(currentPosition, nextPosition, board);
            return;
        }

        if (isForwardMove(currentPosition, nextPosition)) {
            moveForward(currentPosition, nextPosition, board);
            return;
        }

        if (isDiagonalMove(currentPosition, nextPosition)) {
            moveDiagonal(currentPosition, nextPosition, board);
            return;
        }
    }

    private boolean isInitPawn(Position currentPosition) {
        if (direction.equals(Direction.PLUS) && currentPosition.isSameRank(WHITE_PAWN_INIT_RANK)) {
            return true;
        }
        if (direction.equals(Direction.MINUS) && currentPosition.isSameRank(BLACK_PAWN_INIT_RANK)) {
            return true;
        }
        return false;
    }

    private void moveDiagonal(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (!board.containsKey(nextPosition)) {
            throw new IllegalArgumentException("폰은 상대 기물이 있어야만 대각선으로 이동가능합니다.");
        }
        Piece nextPiece = board.get(nextPosition);
        Piece curPiece = board.get(currentPosition);
        if (!board.containsKey(nextPosition) && !curPiece.isOpponent(nextPiece)) {
            throw new IllegalArgumentException("폰은 상대 기물이 있어야만 대각선으로 이동가능합니다.");
        }
        Piece movingPiece = board.remove(currentPosition);
        board.put(nextPosition, movingPiece);
    }

    private boolean isDiagonalMove(Position currentPosition, Position nextPosition) {
        Position leftDiagonal = currentPosition.move(Direction.MINUS, direction);
        Position rightDiagonal = currentPosition.move(Direction.PLUS, direction);
        return leftDiagonal.equals(nextPosition) || rightDiagonal.equals(nextPosition);
    }

    private boolean isForwardMove(Position currentPosition, Position nextPosition) {
        Position forwardPosition = currentPosition.moveRank(direction);
        if (forwardPosition.equals(nextPosition)) {
            return true;
        }
        return false;
    }

    private boolean isForwardTwoMove(Position currentPosition, Position nextPosition) {
        Position forwardPosition = currentPosition.moveRank(direction, 2);
        if (forwardPosition.equals(nextPosition)) {
            return true;
        }
        return false;
    }

    private void moveForward(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (board.containsKey(nextPosition)) {
            throw new IllegalArgumentException("폰의 이동위치에 다른 기물이 있습니다.");
        }
        Piece movingPiece = board.remove(currentPosition);
        board.put(nextPosition, movingPiece);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
}
