package chess.model.piece;

import static chess.model.piece.PieceType.BLACK_BISHOP;
import static chess.model.piece.PieceType.BLACK_KING;
import static chess.model.piece.PieceType.BLACK_KNIGHT;
import static chess.model.piece.PieceType.BLACK_PAWN;
import static chess.model.piece.PieceType.BLACK_QUEEN;
import static chess.model.piece.PieceType.BLACK_ROOK;
import static chess.model.piece.PieceType.NONE;
import static chess.model.piece.PieceType.WHITE_BISHOP;
import static chess.model.piece.PieceType.WHITE_KING;
import static chess.model.piece.PieceType.WHITE_KNIGHT;
import static chess.model.piece.PieceType.WHITE_PAWN;
import static chess.model.piece.PieceType.WHITE_QUEEN;
import static chess.model.piece.PieceType.WHITE_ROOK;

import chess.model.Position;

public abstract class Piece implements MoveStrategy {

    protected final PieceType type;

    protected Piece(PieceType pieceType) {
        this.type = pieceType;
    }

    public abstract boolean canAttack(Position source, Position target);

    public abstract boolean canJump();

    public static Piece from(PieceType pieceType) {
        if (pieceType == BLACK_PAWN || pieceType == WHITE_PAWN) {
            return new Pawn(pieceType);
        }
        if (pieceType == BLACK_ROOK || pieceType == WHITE_ROOK) {
            return new Rook(pieceType);
        }
        if (pieceType == BLACK_KNIGHT || pieceType == WHITE_KNIGHT) {
            return new Knight(pieceType);
        }
        if (pieceType == BLACK_BISHOP || pieceType == WHITE_BISHOP) {
            return new Bishop(pieceType);
        }
        if (pieceType == BLACK_QUEEN || pieceType == WHITE_QUEEN) {
            return new Queen(pieceType);
        }
        if (pieceType == BLACK_KING || pieceType == WHITE_KING) {
            return new King(pieceType);
        }
        return new None(pieceType);
    }

    protected final int calculateRowDifference(Position source, Position target) {
        return target.getRow() - source.getRow();
    }

    protected final int calculateColumnDifference(Position source, Position target) {
        return target.getColumn() - source.getColumn();
    }

    public boolean isEnemy(int turnCount) {
        return !type.isSameColor(turnCount);
    }

    public boolean isAlly(int turnCount) {
        return type.isSameColor(turnCount);
    }

    public boolean isWhite() {
        return type.isWhite();
    }

    public boolean isBlack() {
        return type.isBlack();
    }

    public boolean isExist() {
        return type != NONE;
    }

    public boolean isNone() {
        return type == NONE;
    }

    public boolean isPawn() {
        return type.isPawn();
    }

    @Override
    public String toString() {
        return type.getDisplayName();
    }
}
