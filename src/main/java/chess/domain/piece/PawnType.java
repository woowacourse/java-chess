package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Tile;

import java.util.Arrays;

enum PawnType {
    BLACK_PAWN(PieceColor.BLACK, false, false, MovingRange.BLACK_PAWN_RANGE),
    BLACK_START_PAWN(PieceColor.BLACK, true, false, MovingRange.BLACK_START_PAWN_RANGE),
    BLACK_ATTACK_PAWN(PieceColor.BLACK, false, true, MovingRange.BLACK_PAWN_ATTACK_RANGE),
    BLACK_START_ATTACK_PAWN(PieceColor.BLACK, true, true, MovingRange.BLACK_PAWN_ATTACK_RANGE),
    WHITE_PAWN(PieceColor.WHITE, false, false, MovingRange.WHITE_PAWN_RANGE),
    WHITE_START_PAWN(PieceColor.WHITE, true, false, MovingRange.WHITE_START_PAWN_RANGE),
    WHITE_ATTACK_PAWN(PieceColor.WHITE, false, true, MovingRange.WHITE_PAWN_ATTACK_RANGE),
    WHITE_START_ATTACK_PAWN(PieceColor.WHITE, true, true, MovingRange.WHITE_PAWN_ATTACK_RANGE);

    private final PieceColor pieceColor;
    private final boolean isStart;
    private final boolean haveTarget;
    private final MovingRange movingRange;

    PawnType(PieceColor pieceColor, boolean isStart, boolean haveTarget, MovingRange movingRange) {
        this.pieceColor = pieceColor;
        this.isStart = isStart;
        this.haveTarget = haveTarget;
        this.movingRange = movingRange;
    }

    public static MovingRange getRange(PieceColor pieceColor, Tile current, boolean haveTarget) {
        return Arrays.asList(PawnType.values()).stream()
                .filter(type -> type.pieceColor.equals(pieceColor) && type.haveTarget == haveTarget && distinctStart(current, type))
                .map(type -> type.movingRange)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("폰 전략 없음"))
                ;
    }

    private static boolean distinctStart(Tile current, PawnType type) {
        return (current.isEqualRow(BoardInitializer.WHITE_PAWNS_ROW) || current.isEqualRow(BoardInitializer.BLACK_PAWNS_ROW)) == type.isStart;
    }
}

