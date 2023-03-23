package chess.domain;

import chess.domain.dto.request.MoveRequest;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.king.KingStrategy;
import chess.domain.strategy.knight.KnightStrategy;
import chess.domain.strategy.pawn.PawnStrategy;
import chess.domain.strategy.queen.QueenStrategy;
import chess.domain.strategy.rook.RookStrategy;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Shape {

    PAWN('p', 'P', 1, (request) -> new PawnStrategy().validateDirection(request)),
    KING('k', 'K', 0, (request) -> new KingStrategy().validateDirection(request)),
    QUEEN('q', 'Q', 9, (request) -> new QueenStrategy().validateDirection(request)),
    ROOK('r', 'R', 5, (request) -> new RookStrategy().validateDirection(request)),
    BISHOP('b', 'B', 3, (request) -> new BishopStrategy().validateDirection(request)),
    KNIGHT('n', 'N', 2.5, (request) -> new KnightStrategy().validateDirection(request));

    private final char whiteName;
    private final char blackName;
    private final Score score;
    private final Consumer<MoveRequest> validateDirection;

    Shape(final char whiteName, final char blackName, final double score, final Consumer<MoveRequest> validateDirection) {
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.score = Score.from(score);
        this.validateDirection = validateDirection;
    }

    public void move(final MoveRequest request) {
        this.validateDirection.accept(request);
    }

    public static Shape findShapeByWhiteName(final char whiteName) {
        return Arrays.stream(values())
                .filter(shape -> shape.whiteName == whiteName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public char getNameByColor(final Color color) {
        if (color.isWhite()) {
            return this.whiteName;
        }
        return this.blackName;
    }

    public char getWhiteName() {
        return whiteName;
    }

    public double getScore() {
        return score.getValue();
    }

}
