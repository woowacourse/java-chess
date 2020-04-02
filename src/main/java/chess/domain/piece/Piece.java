package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Objects;

public class Piece {
    private PieceType pieceType;
    private Position position;

    public Piece(PieceType pieceType, Position position) {
        this.pieceType = pieceType;
        this.position = position;
    }

    public static Piece createBlank(Position position) {
        return new Piece(PieceType.BLANK, position);
    }

    public static Piece createWhitePawn(Position position) {
        return new Piece(PieceType.WHITE_PAWN, position);
    }

    public static Piece createBlackPawn(Position position) {
        return new Piece(PieceType.BLACK_PAWN, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return new Piece(PieceType.WHITE_KNIGHT, position);
    }

    public static Piece createBlackKnight(Position position) {
        return new Piece(PieceType.BLACK_KNIGHT, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return new Piece(PieceType.WHITE_BISHOP, position);
    }

    public static Piece createBlackBishop(Position position) {
        return new Piece(PieceType.BLACK_BISHOP, position);
    }

    public static Piece createWhiteRook(Position position) {
        return new Piece(PieceType.WHITE_ROOK, position);
    }

    public static Piece createBlackRook(Position position) {
        return new Piece(PieceType.BLACK_ROOK, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return new Piece(PieceType.WHITE_QUEEN, position);
    }

    public static Piece createBlackQueen(Position position) {
        return new Piece(PieceType.BLACK_QUEEN, position);
    }

    public static Piece createWhiteKing(Position position) {
        return new Piece(PieceType.WHITE_KING, position);
    }

    public static Piece createBlackKing(Position position) {
        return new Piece(PieceType.BLACK_KING, position);
    }

    public Piece move(Position toPosition) {
        return new Piece(pieceType, toPosition);
    }

    public Board movePiece(Board board, Piece toPiece, Team currentTurn) {
        return pieceType.movePieceWithTurnValidation(board, this, toPiece, currentTurn);
    }

    public boolean isKing() {
        return pieceType == PieceType.WHITE_KING | pieceType == PieceType.BLACK_KING;
    }

    public boolean isOtherTeam(Piece nextPiece) {
        return this.pieceType.isOtherTeam(nextPiece.pieceType);
    }

    public boolean isSameTeam(Team team) {
        return this.pieceType.isSameTeam(team);
    }

    public boolean isPawn() {
        return pieceType == PieceType.WHITE_PAWN | pieceType == PieceType.BLACK_PAWN;
    }

    public boolean isBlank() {
        return pieceType == PieceType.BLANK;
    }

    public char getRepresentation() {
        return pieceType.representation();
    }

    public Position getPosition() {
        return position;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public int getRow() {
        return position.getY();
    }

    public double score() {
        return pieceType.score();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType &&
                Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, position);
    }

    @Override
    public String toString() {
        return String.valueOf(pieceType.representation());
    }
}
