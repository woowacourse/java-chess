package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.piece.strategy.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PieceKind {
    KING("K", 0, new KingMoveStrategy(),
        Collections.singletonList(XPosition.E),
        Collections.singletonList(YPosition.ONE)),
    QUEEN("Q", 9, new QueenMoveStrategy(),
        Collections.singletonList(XPosition.D),
        Collections.singletonList(YPosition.ONE)),
    KNIGHT("N", 2.5, new KnightMoveStrategy(),
        Arrays.asList(XPosition.B, XPosition.G),
        Collections.singletonList(YPosition.ONE)),
    BISHOP("B", 3, new BishopMoveStrategy(),
        Arrays.asList(XPosition.C, XPosition.F),
        Collections.singletonList(YPosition.ONE)),
    ROOK("R", 5, new RookMoveStrategy(),
        Arrays.asList(XPosition.A, XPosition.H),
        Collections.singletonList(YPosition.ONE)),
    PAWN("P", 1, new PawnMoveStrategy(),
        Arrays.asList(XPosition.values()),
        Collections.singletonList(YPosition.TWO)),
    VOID(".", 0, new VoidMoveStrategy(),
        Arrays.asList(XPosition.values()),
        Arrays.asList(YPosition.THREE, YPosition.FOUR));

    private final String symbol;
    private final double point;
    private final MoveStrategy moveStrategy;
    private final List<XPosition> initialXPositions;
    private final List<YPosition> initialYPositions;

    PieceKind(String symbol, double point, MoveStrategy moveStrategy, List<XPosition> initialXPositions,
              List<YPosition> initialYPositions) {
        this.symbol = symbol;
        this.point = point;
        this.moveStrategy = moveStrategy;
        this.initialXPositions = initialXPositions;
        this.initialYPositions = initialYPositions;
    }

    public List<XPosition> bringInitialXPositions() {
        return this.initialXPositions;
    }

    public List<YPosition> bringInitialYPositions() {
        return this.initialYPositions;
    }

    public boolean isSameKind(PieceKind pieceKind) {
        return this == pieceKind;
    }
    public String getName(PieceColor pieceColor) {
        if (pieceColor.isSameColor(PieceColor.WHITE)) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public double point() {
        return this.point;
    }

    public void movable(Position source, Position target) {
        this.moveStrategy.move(source, target);
    }
}
