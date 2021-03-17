package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

public abstract class BasicMoveStrategy implements MoveStrategy {

    protected static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    @Override
    public void move(Position source, Position target, Board board) {
        checkValidMove(source, target, board);

        Piece originalPiece = board.checkPieceAtPosition(source);
        board.putPieceAtPosition(target, originalPiece);

        board.putPieceAtPosition(source, VOID_PIECE);
    }

    protected void checkPositionsOnBoard(Position source, Position target) {
        checkWithinBoardPosition(source);
        checkWithinBoardPosition(target);
    }

    protected void checkIsNotSameTeam(Position source, Position target, Board board) {
        Piece sourcePiece = board.checkPieceAtPosition(source);
        Piece targetPiece = board.checkPieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    private void checkWithinBoardPosition(Position target) {
        if (target == null) {
            throw new InvalidMoveException(Piece.OUT_OF_BOUND_MESSAGE);
        }
    }

    protected boolean isStay(Position source, Position target) {
        return source.computeHorizontalDistance(target) == 0 &&
            source.computeVerticalDistance(target) == 0;
    }

    protected boolean isLineMove(Position source, Position target) {
        return (source.computeHorizontalDistance(target) == 0 ||
            source.computeVerticalDistance(target) == 0);
    }

    protected boolean isDiagonalMove(Position source, Position target) {
        return (Math.abs(source.computeHorizontalDistance(target))
            == Math.abs(source.computeVerticalDistance(target)));
    }

    protected void checkClearPath(Position source, Position target, MoveDirection moveDirection, Board board) {
        int moveNumber = calculateMoveNumber(source, target);
        Position pathPosition = source;
        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        for (int i = 0; i < moveNumber - 1; i++) {
            pathPosition = Position.of(pathPosition.getXPosition().moveUnit(xVector),
                pathPosition.getYPosition().moveUnit(yVector));
            checkIfClear(board, pathPosition);
        }
    }

    private void checkIfClear(Board board, Position pathPosition) {
        if (!board.checkPieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new InvalidMoveException(Piece.UNABLE_CROSS_MESSAGE);
        }
    }

    private int calculateMoveNumber(Position source, Position target) {
        int verticalMoveNumber = source.computeVerticalDistance(target);
        if (verticalMoveNumber != 0) {
            return Math.abs(verticalMoveNumber);
        }
        return Math.abs(source.computeHorizontalDistance(target));
    }

    abstract void checkValidMove(Position source, Position target, Board board);
}
