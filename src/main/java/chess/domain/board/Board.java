package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.domain.piece.strategy.MoveDirection;

import java.util.Map;

public class Board {

    private static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    private final Map<Position, Piece> board;

    public Board() {
        this.board = InitializedBoard.board();
    }

    public void move(Position source, Position target) {
        checkIsNotSameTeam(source, target);
        if (source.isLineMove(target) || source.isDiagonalMove(target)) {
            checkClearPath(source, target);
        }

        Piece originalPiece = checkPieceAtPosition(source);

        if (originalPiece.isPawn()) {
            checkDirection(source, target);
            checkMoveType(source, target);
        }

        putPieceAtPosition(target, originalPiece);
        putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkIsNotSameTeam(Position source, Position target) {
        Piece sourcePiece = checkPieceAtPosition(source);
        Piece targetPiece = checkPieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    private void checkClearPath(Position source, Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        Position pathPosition = new Position(source);

        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        while (!pathPosition.equals(target)) {
            pathPosition.moveUnit(xVector, yVector);
            checkIfClear(pathPosition);
        }
    }

    private void checkIfClear(Position pathPosition) {
        if (!checkPieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new InvalidMoveException(Piece.UNABLE_CROSS_MESSAGE);
        }
    }

    private void checkDirection(Position source, Position target) {
        Piece piece = checkPieceAtPosition(source);
        if (!piece.isMovingForward(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private void checkMoveType(Position source, Position target) {
        if (isVoid(target) && source.isDiagonalMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }

        if (!isVoid(target) && source.isLineMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private boolean isVoid(Position target) {
        Piece targetPiece = checkPieceAtPosition(target);
        return targetPiece.equals(VOID_PIECE);
    }

    public Piece checkPieceAtPosition(Position position) {
        return board.get(position);
    }

    public void putPieceAtPosition(Position position, Piece piece) {
        board.put(position, piece);
    }
}
