package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private static final Map<PieceType, Piece> ALL_PIECES = new HashMap<>();

    static {
        ALL_PIECES.put(PieceType.FIRST_WHITE_PAWN, new Pawn(PieceType.FIRST_WHITE_PAWN));
        ALL_PIECES.put(PieceType.FIRST_BLACK_PAWN, new Pawn(PieceType.FIRST_BLACK_PAWN));
        ALL_PIECES.put(PieceType.WHITE_PAWN, new Pawn(PieceType.WHITE_PAWN));
        ALL_PIECES.put(PieceType.BLACK_PAWN, new Pawn(PieceType.BLACK_PAWN));
        ALL_PIECES.put(PieceType.WHITE_KNIGHT, new Knight(PieceType.WHITE_KNIGHT));
        ALL_PIECES.put(PieceType.BLACK_KNIGHT, new Knight(PieceType.BLACK_KNIGHT));
        ALL_PIECES.put(PieceType.WHITE_ROOK, new Rook(PieceType.WHITE_ROOK));
        ALL_PIECES.put(PieceType.BLACK_ROOK, new Rook(PieceType.BLACK_ROOK));
        ALL_PIECES.put(PieceType.WHITE_BISHOP, new Bishop(PieceType.WHITE_BISHOP));
        ALL_PIECES.put(PieceType.BLACK_BISHOP, new Bishop(PieceType.BLACK_BISHOP));
        ALL_PIECES.put(PieceType.WHITE_QUEEN, new Queen(PieceType.WHITE_QUEEN));
        ALL_PIECES.put(PieceType.BLACK_QUEEN, new Queen(PieceType.BLACK_QUEEN));
        ALL_PIECES.put(PieceType.WHITE_KING, new King(PieceType.WHITE_KING));
        ALL_PIECES.put(PieceType.BLACK_KING, new King(PieceType.BLACK_KING));
        ALL_PIECES.put(PieceType.BLANK, new Blank(PieceType.BLANK));
    }

    protected PieceType pieceType;

    Piece(final PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public static Piece of(final PieceType pieceType) {
        return ALL_PIECES.get(pieceType);
    }

    public boolean isMovable(final Board board, final Position fromPosition, final Position toPosition) {
        if (pieceType.possiblePositions(board, this, fromPosition).contains(toPosition)) {
            return true;
        }
        throw new PieceImpossibleMoveException("해당 포지션으로 이동할 수 없습니다.");
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isBlank() {
        return this instanceof Blank;
    }

    public boolean isOtherTeam(final Piece piece) {
        return pieceType.isOtherTeam(piece.pieceType);
    }

    public boolean isSameTeam(final Piece piece) {
        return pieceType.isSameTeam(piece.pieceType);
    }

    public boolean isSameTeam(final Team currentTurn) {
        return pieceType.isSameTeam(currentTurn);
    }

    public List<Direction> getDirections() {
        return pieceType.getDirections();
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public String getName() {
        return pieceType.name();
    }

    public abstract Piece getNextPiece();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }

    @Override
    public String toString() {
        return pieceType.getRepresentation();
    }
}