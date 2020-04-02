package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.*;

import java.util.Arrays;
import java.util.List;

public enum PieceType {
    FIRST_WHITE_PAWN(new FirstWhitePawnMoveStrategy(), 1),
    WHITE_PAWN(new WhitePawnMoveStrategy(), 1),
    FIRST_BLACK_PAWN(new FirstBlackPawnMoveStrategy(), 1),
    BLACK_PAWN(new BlackPawnMoveStrategy(), 1),

    KNIGHT(new KnightMoveStrategy(), 2.5),
    BISHOP(new BishopMoveStrategy(), 3),
    ROOK(new RookMoveStrategy(), 5),
    QUEEN(new QueenMoveStrategy(), 9),
    KING(new KingMoveStrategy(), 0),
    BLANK(new BlankMoveStrategy(), 0);

    private final MoveStrategy moveStrategy;
    private final double score;

    PieceType(MoveStrategy moveStrategy, double score) {
        this.moveStrategy = moveStrategy;
        this.score = score;
    }

    public static boolean isPawn(final Piece piece) {
        return Arrays.asList(FIRST_WHITE_PAWN, FIRST_BLACK_PAWN, WHITE_PAWN, BLACK_PAWN).contains(piece.pieceType);
    }

    public List<Position> getPossiblePositions(Board board, Piece piece) {
        return moveStrategy.getPossiblePositions(board, piece);
    }

    public double score() {
        return score;
    }

    public PieceType notFirstStep() {
        if (this == FIRST_WHITE_PAWN) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }
}
