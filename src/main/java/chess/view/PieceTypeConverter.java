package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceTypeConverter {

    PAWN(PieceType.PAWN, 'p'),
    ROOK(PieceType.ROOK, 'r'),
    KNIGHT(PieceType.KNIGHT, 'n'),
    BISHOP(PieceType.BISHOP, 'b'),
    QUEEN(PieceType.QUEEN, 'q'),
    KING(PieceType.KING, 'k');

    private static final char EMPTY_SPACE = '·';

    private final PieceType pieceType;
    private final char symbol;

    PieceTypeConverter(final PieceType pieceType, final char symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }

    public static char getEmptySymbol() {
        return EMPTY_SPACE;
    }

    public static char getSymbol(final PieceType pieceType, final Color color) {
        char symbol = getSymbolByPieceType(pieceType);
        if (color.equals(Color.WHITE)) {
            return symbol;
        }
        return Character.toUpperCase(symbol);
    }

    private static char getSymbolByPieceType(final PieceType pieceType) {
        return Arrays.stream(values())
                .filter(pieceTypeConverter -> pieceTypeConverter.pieceType == pieceType)
                .findFirst()
                .map(pieceTypeConverter -> pieceTypeConverter.symbol)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 말 종류입니다."));
    }
}
