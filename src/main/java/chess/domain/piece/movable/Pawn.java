package chess.domain.piece.movable;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public class Pawn extends AbstractPiece {

    private static final PieceName PIECE_NAME = PieceName.PAWN;
    private static final PieceScore PIECE_SCORE = PieceScore.PAWN;
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
        super(PIECE_NAME, PIECE_SCORE, pieceDirections);
        this.maximumCountToMove = movableCount;
        this.pieceDirections = pieceDirections;
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
