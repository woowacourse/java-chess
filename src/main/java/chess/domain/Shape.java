package chess.domain;

import chess.dto.request.MoveRequest;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.king.KingStrategy;
import chess.domain.strategy.knight.KnightStrategy;
import chess.domain.strategy.pawn.PawnStrategy;
import chess.domain.strategy.queen.QueenStrategy;
import chess.domain.strategy.rook.RookStrategy;

import java.util.function.Consumer;

public enum Shape {

    PAWN(1, (request) -> new PawnStrategy().validateDirection(request)),
    KING(0, (request) -> new KingStrategy().validateDirection(request)),
    QUEEN(9, (request) -> new QueenStrategy().validateDirection(request)),
    ROOK(5, (request) -> new RookStrategy().validateDirection(request)),
    BISHOP(3, (request) -> new BishopStrategy().validateDirection(request)),
    KNIGHT(2.5, (request) -> new KnightStrategy().validateDirection(request));

    private final Score score;
    private final Consumer<MoveRequest> validateDirection;

    Shape(final double score, final Consumer<MoveRequest> validateDirection) {
        this.score = Score.from(score);
        this.validateDirection = validateDirection;
    }

    public void move(final MoveRequest request) {
        this.validateDirection.accept(request);
    }

    public double getScore() {
        return score.getValue();
    }

}
