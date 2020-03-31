package chess.domains.piece;

import chess.domains.position.Direction;
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
    private static final String INVALID_DIAGONAL_MOVE_OF_PAWN_ERR_MSG = "폰은 상대말을 잡는 경우 이 외에 대각선으로 이동할 수 없습니다.";
    private static final String INVALID_VERTICAL_MOVE_OF_PAWN_ERR_MSG = "폰은 앞에 있는 상대를 잡을 수 없습니다.";
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

    public String name() {
        if (pieceColor == PieceColor.BLACK) {
            return type.getName().toUpperCase();
        }
        return type.getName();
    }

    public double score() {
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
            validPawnMove(targetPiece, source, target);
        }
    }

    private void validPawnMove(Piece targetPiece, Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);

        if (direction.isDiagonal() && targetPiece.is(PieceType.BLANK)) {
            throw new IllegalArgumentException(INVALID_DIAGONAL_MOVE_OF_PAWN_ERR_MSG);
        }

        if (direction.isVertical() && !targetPiece.is(PieceType.BLANK)) {
            throw new IllegalArgumentException(INVALID_VERTICAL_MOVE_OF_PAWN_ERR_MSG);
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
