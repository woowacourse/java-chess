package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.*;
import java.util.Arrays;

public enum PieceKind {

    KING("K", 0, new KingMoveStrategy()),
    QUEEN("Q", 9, new QueenMoveStrategy()),
    KNIGHT("N", 2.5, new KnightMoveStrategy()),
    BISHOP("B", 3,  new BishopMoveStrategy()),
    ROOK("R", 5,  new RookMoveStrategy()),
    PAWN("P", 1, new PawnMoveStrategy()),
    VOID(".", 0, new VoidMoveStrategy());

    public static final String INVALID_MATCH_PIECE = "맞는 말의 종류가 없습니다.";

    private final String symbol;
    private final double point;
    private final MoveStrategy moveStrategy;

    PieceKind(String symbol, double point, MoveStrategy moveStrategy) {
        this.symbol = symbol;
        this.point = point;
        this.moveStrategy = moveStrategy;
    }

    public String getName(final PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public boolean isSameKind(String rawPieceKind) {
        return symbol.equals(rawPieceKind);
    }

    public static PieceKind matchPieceKind(String rawPieceKind) {
        return Arrays.stream(values())
            .filter(element -> element.isSameKind(rawPieceKind))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_MATCH_PIECE));
    }

    public double point() {
        return this.point;
    }

    public void movable(final Position source, final Position target) {
        this.moveStrategy.move(source, target);
    }
}
