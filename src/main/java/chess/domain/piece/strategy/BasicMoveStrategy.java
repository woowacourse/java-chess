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
        if (source == null || target == null) {
            throw new InvalidMoveException(Piece.OUT_OF_BOUND_MESSAGE);
        }
    }

    protected void checkIsNotSameTeam(Position source, Position target, Board board) { //TODO board 책임 전가
        Piece sourcePiece = board.checkPieceAtPosition(source);
        Piece targetPiece = board.checkPieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    protected boolean isStay(Position source, Position target) {
        return source.computeHorizontalDistance(target) == 0 &&
            source.computeVerticalDistance(target) == 0;
    } //TODO 나중에 안움직이는지 확인

    abstract void checkValidMove(Position source, Position target, Board board);
}
