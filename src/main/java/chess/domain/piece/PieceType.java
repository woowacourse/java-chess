package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.board.Square;
import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.Strategy;
import chess.domain.piece.strategy.vector.DirectVector;
import chess.domain.piece.strategy.vector.SlidingVector;
import java.util.List;

public enum PieceType {

    KING(0, new DirectStrategy(DirectVector.ofKing())),
    QUEEN(9, new SlidingStrategy(SlidingVector.ofQueen())),
    ROOK(5, new SlidingStrategy(SlidingVector.ofRook())),
    BISHOP(3, new SlidingStrategy(SlidingVector.ofBishop())),
    KNIGHT(2.5, new DirectStrategy(DirectVector.ofKnight())),
    BLACK_PAWN(1, new DirectStrategy(DirectVector.ofPawnByColor(BLACK))),
    WHITE_PAWN(1, new DirectStrategy(DirectVector.ofPawnByColor(WHITE))),
    ;

    private final double score;
    private final Strategy strategy;

    PieceType(final double score, final Strategy strategy) {
        this.score = score;
        this.strategy = strategy;
    }

    public List<Square> findRoute(final Square source, final Square destination) {
        return strategy.findRoute(source, destination);
    }

    public double getScore() {
        return score;
    }
}
