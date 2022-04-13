package chess.domain.piece.movable;

import chess.domain.Color;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public class Pawn extends AbstractPiece {

    private static final PieceType PIECE_TYPE = PieceType.PAWN;
    private static final int FIRST_MOVABLE_COUNT = 2;
    private static final int NORMAL_MOVABLE_COUNT = 1;
    private static final int MOVABLE_COUNT_TO_ATTACK = 1;
    private static final Pawn WHITE_PAWN = new Pawn(PieceDirections.WHITE_PAWN);
    private static final Pawn BLACK_PAWN = new Pawn(PieceDirections.BLACK_PAWN);

    private final int maximumCountToMove;
    private final PieceDirections pieceDirections;

    private Pawn(final PieceDirections pieceDirections) {
        this(FIRST_MOVABLE_COUNT, pieceDirections);
    }

    private Pawn(final int movableCount, final PieceDirections pieceDirections) {
        super(PIECE_TYPE, pieceDirections);
        this.maximumCountToMove = movableCount;
        this.pieceDirections = pieceDirections;
    }

    public static Pawn getPawnByColor(final Color color) {
        if (color == Color.WHITE) {
            return WHITE_PAWN;
        }
        if (color == Color.BLACK) {
            return BLACK_PAWN;
        }
        throw new IllegalArgumentException("해당 색상의 폰은 생성할 수 없습니다.");
    }

    public static Pawn getWhitePawn() {
        return WHITE_PAWN;
    }

    public static Pawn getBlackPawn() {
        return BLACK_PAWN;
    }

    @Override
    public Piece move() {
        return new Pawn(NORMAL_MOVABLE_COUNT, pieceDirections);
    }

    @Override
    protected boolean isRouteSizeEnoughToMove(int routeSize) {
        return routeSize <= maximumCountToMove;
    }

    @Override
    protected boolean isRouteSizeEnoughToAttack(int routeSize) {
        return routeSize <= MOVABLE_COUNT_TO_ATTACK;
    }

    @Override
    public final boolean isPawn() {
        return true;
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
