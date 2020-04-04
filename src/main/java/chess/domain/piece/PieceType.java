package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.*;
import chess.domain.util.Direction;
import chess.domain.util.Directions;

import java.util.List;

public enum PieceType {
    FIRST_WHITE_PAWN(new PawnMoveStrategy(), Directions.FIRST_WHITE_PAWN_DIRECTION, 1),
    FIRST_BLACK_PAWN(new PawnMoveStrategy(), Directions.FIRST_BLACK_PAWN_DIRECTION, 1),
    WHITE_PAWN(new PawnMoveStrategy(), Directions.WHITE_PAWN_DIRECTION, 1),
    BLACK_PAWN(new PawnMoveStrategy(), Directions.BLACK_PAWN_DIRECTION, 1),

    KNIGHT(new SingleMoveStrategy(), Directions.KNIGHT_DIRECTION, 2.5),
    KING(new SingleMoveStrategy(), Directions.KING_DIRECTION, 0),

    BISHOP(new MultipleMoveStrategy(), Directions.BISHOP_DIRECTION, 3),
    ROOK(new MultipleMoveStrategy(), Directions.ROOK_DIRECTION, 5),
    QUEEN(new MultipleMoveStrategy(), Directions.QUEEN_DIRECTION, 9),

    BLANK(new BlankStrategy(), Directions.BLANK_DIRECTION, 0);

    private final MoveStrategy moveStrategy;
    private final Directions directions;
    private final double score;

    PieceType(final MoveStrategy moveStrategy, final Directions directions, final double score) {
        this.moveStrategy = moveStrategy;
        this.directions = directions;
        this.score = score;
    }

    public List<Position> possiblePositions(final Board board, final Piece piece) {
        return moveStrategy.possiblePositions(board, piece);
    }

    public PieceType notFirstStep() {
        if (this == FIRST_WHITE_PAWN) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }

    public double score() {
        return score;
    }

    public List<Direction> directions() {
        return directions.directions();
    }
}