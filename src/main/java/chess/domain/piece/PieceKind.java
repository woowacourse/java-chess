package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.*;

import java.util.Arrays;
import java.util.List;

public enum PieceKind {
    KING("K", Arrays.asList('e'), Arrays.asList(1), new KingMoveStrategy()),
    QUEEN("Q", Arrays.asList('d'), Arrays.asList(1), new QueenMoveStrategy()),
    KNIGHT("N", Arrays.asList('b', 'g'), Arrays.asList(1), new KnightMoveStrategy()),
    BISHOP("B", Arrays.asList('c', 'f'), Arrays.asList(1), new BishopMoveStrategy()),
    ROOK("R", Arrays.asList('a', 'h'), Arrays.asList(1), new RookMoveStrategy()),
    PAWN("P", Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(2), new PawnMoveStrategy()),
    VOID(".", Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(3, 4), new VoidMoveStrategy());

    private final String symbol;
    private final List<Character> initialXPositions;
    private final List<Integer> initialYPositions;
    private final MoveStrategy moveStrategy;

    PieceKind(String symbol, List<Character> initialXPositions,
              List<Integer> initialYPositions, MoveStrategy moveStrategy) {
        this.symbol = symbol;
        this.initialXPositions = initialXPositions;
        this.initialYPositions = initialYPositions;
        this.moveStrategy = moveStrategy;
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

    public void movable(Position source, Position target) {
        this.moveStrategy.move(source, target);
    }
}
