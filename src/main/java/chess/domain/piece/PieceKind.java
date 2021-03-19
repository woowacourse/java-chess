package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.*;

import java.util.Arrays;
import java.util.List;

public enum PieceKind {
    KING("K", 0, new KingMoveStrategy(), Arrays.asList('e'), Arrays.asList(1)),
    QUEEN("Q", 9, new QueenMoveStrategy(), Arrays.asList('d'), Arrays.asList(1)),
    KNIGHT("N", 2.5, new KnightMoveStrategy(), Arrays.asList('b', 'g'), Arrays.asList(1)),
    BISHOP("B", 3,  new BishopMoveStrategy(), Arrays.asList('c', 'f'), Arrays.asList(1)),
    ROOK("R", 5,  new RookMoveStrategy(), Arrays.asList('a', 'h'), Arrays.asList(1)),
    PAWN("P", 1, new PawnMoveStrategy(), Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(2)),
    VOID(".", 0, new VoidMoveStrategy(), Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(3, 4));

    private final String symbol;
    private final double point;
    private final MoveStrategy moveStrategy;
    private final List<Character> initialXPositions;
    private final List<Integer> initialYPositions;

    PieceKind(String symbol, double point, MoveStrategy moveStrategy, List<Character> initialXPositions,
              List<Integer> initialYPositions) {
        this.symbol = symbol;
        this.point = point;
        this.moveStrategy = moveStrategy;
        this.initialXPositions = initialXPositions;
        this.initialYPositions = initialYPositions;
    }

    public List<Character> bringInitialXPositions() {
        return this.initialXPositions;
    }

    public List<Integer> bringInitialYPositions() {
        return this.initialYPositions;
    }

    public String getName(PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
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
