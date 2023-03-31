package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.ordinary.*;
import chess.domain.piece.pawn.Pawn;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static chess.domain.movement.Movement.*;

public enum PieceType {
    KING(0, List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL), King::new),
    QUEEN(9, List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
            CONTINUOUS_STRAIGHT, CONTINUOUS_DIAGONAL), Queen::new),
    ROOK(5, List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT), Rook::new),
    KNIGHT(2.5, List.of(CONTINUOUS_L_SHAPE), Knight::new),
    BISHOP(3, List.of(CONTINUOUS_DIAGONAL, DISCONTINUOUS_DIAGONAL), Bishop::new),
    PAWN(1, List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL), Pawn::new),
    EMPTY(0, Collections.emptyList(), (ignored) -> new Empty());

    private final double score;
    private final List<Movement> movements;
    private final Function<Team, Piece> function;

    PieceType(final double score, final List<Movement> movements, final Function<Team, Piece> function) {
        this.score = score;
        this.movements = movements;
        this.function = function;
    }

    public double getScore() {
        return score;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public Piece getPiece(Team team) {
        return this.function.apply(team);
    }
}
