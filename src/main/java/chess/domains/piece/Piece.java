package chess.domains.piece;

import chess.domains.position.Position;

import java.util.Objects;

public abstract class Piece {
    static final int NO_MOVE = 0;
    static final int ONE_BLOCK = 1;
    static final int TWO_BLOCKS = 2;
    static final int ONE_BLOCK_UP = 1;
    static final int ONE_BLOCK_DOWN = -1;
    static final int TWO_BLOCKS_UP = 2;
    static final int TWO_BLOCKS_DOWN = -2;
    private static final String INVALID_MOVE_TO_SAME_POSITION_ERR_MSG = "자신의 말 위치로 이동할 수 없습니다.";
    private static final String INVALID_MOVE_TO_ILLEGAL_POSITION_ERR_MSG = "말의 규칙에 어긋나는 위치로 이동할 수 없습니다.";
    private static final String INVALID_MOVE_OPPONENT_PIECE_ERR_MSG = "상대방의 말을 움직일 수 없습니다.";

    protected final PieceColor pieceColor;
    private final PieceType type;

    public Piece(PieceColor pieceColor, PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    public abstract boolean isValidMove(Position currentPosition, Position targetPosition);

    public boolean isMine(PieceColor pieceColor) {
        return this.pieceColor == pieceColor;
    }

    public boolean isMine(Piece piece) {
        return isMine(piece.pieceColor);
    }

    public boolean is(PieceType pieceType) {
        return this.type == pieceType;
    }

    public String getName() {
        if (pieceColor == PieceColor.BLACK) {
            return type.getName().toUpperCase();
        }
        return type.getName();
    }

    public String getChessPiece() {
        if (pieceColor == PieceColor.BLACK) {
            return type.getBlackPiece();
        }
        return type.getWhitePiece();
    }

    public double getScore() {
        return type.getScore();
    }

    public void checkSameColorWith(PieceColor teamColor) {
        if (this.pieceColor != teamColor) {
            throw new IllegalArgumentException(INVALID_MOVE_OPPONENT_PIECE_ERR_MSG);
        }
    }

    public void validMove(Piece targetPiece, Position source, Position target) {
        if (this.isMine(targetPiece)) {
            throw new IllegalStateException(INVALID_MOVE_TO_SAME_POSITION_ERR_MSG);
        }

        if (!this.isValidMove(source, target)) {
            throw new IllegalArgumentException(INVALID_MOVE_TO_ILLEGAL_POSITION_ERR_MSG);
        }

        if (is(PieceType.PAWN)) {
            ((Pawn) this).validPawnMove(targetPiece, source, target);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return pieceColor == piece.pieceColor &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
