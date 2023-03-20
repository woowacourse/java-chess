package chess.domain;

import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.king.KingStrategy;
import chess.domain.strategy.knight.KnightStrategy;
import chess.domain.strategy.pawn.PawnStrategy;
import chess.domain.strategy.queen.QueenStrategy;
import chess.domain.strategy.rook.RookStrategy;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Shape {

    PAWN('p', 'P', (request) -> new PawnStrategy().validateDirection(request)),
    KING('k', 'K', (request) -> new KingStrategy().validateDirection(request)),
    QUEEN('q', 'Q', (request) -> new QueenStrategy().validateDirection(request)),
    ROOK('r', 'R', (request) -> new RookStrategy().validateDirection(request)),
    BISHOP('b', 'B', (request) -> new BishopStrategy().validateDirection(request)),
    KNIGHT('n', 'N', (request) -> new KnightStrategy().validateDirection(request));

    private final char whiteName;
    private final char blackName;
    private final Consumer<MoveRequest> validateDirection;

    Shape(char whiteName, char blackName, final Consumer<MoveRequest> movePosition) {
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.validateDirection = movePosition;
    }

    public void move(final MoveRequest request) {
        this.validateDirection.accept(request);
    }

    public static Shape findShapeByWhiteName(char whiteName) {
        return Arrays.stream(values())
                .filter(shape -> shape.whiteName == whiteName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public char getNameByColor(String color) {
        if (color.equals("white")) {
            return this.whiteName;
        }
        return this.blackName;
    }

    public char getWhiteName() {
        return whiteName;
    }

}
