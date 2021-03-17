package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

public abstract class BasicMoveStrategy implements MoveStrategy {

    protected static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    public void checkBasicMoveRule(Position source, Position target, Board board) {
        checkWithinBoardPosition(source);
        checkWithinBoardPosition(target);
    }

    public void checkIsNotSameTeam(Position source, Position target, Board board) {
        Piece sourcePiece = board.checkPieceAtPosition(source);
        Piece targetPiece = board.checkPieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    public void checkWithinBoardPosition(Position target) {
        if (target == null) {
            throw new InvalidMoveException(Piece.OUT_OF_BOUND_MESSAGE);
        }
    }
}
